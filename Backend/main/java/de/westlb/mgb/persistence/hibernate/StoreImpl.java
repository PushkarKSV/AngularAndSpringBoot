package de.westlb.mgb.persistence.hibernate;

import java.io.File;
import java.io.FileFilter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import de.westlb.mgb.model.impl.MandantImpl;
import de.westlb.mgb.persistence.PersistenceException;
import de.westlb.mgb.persistence.Session;
import de.westlb.mgb.persistence.Store;


/**
 * MGB store implementation based on Hibernate
 * 
 * @author Manfred Boerner
 */
public class StoreImpl implements Store, Serializable {
    private static final long serialVersionUID = -8291535717677565023L;

    private static final String OS_CACHE_PROPERTIES = "oscache.properties";
	
	private static Logger logger = Logger.getLogger(StoreImpl.class);
	
	private Configuration cfg = new Configuration();
	private Properties properties = null;
	
	/* 
	 * using two factories to get a batch session an a non-batch session
	 * 
	 * batch session: performance improvement for converter
	 * non batch session: java.sql.Blob object of ReportImageImpl can not be processed in batch mode
	 */
    private SessionFactory sessionFactory;
    private SessionFactory batchSessionFactory;
    
	private List<Session> sessions = new ArrayList<Session>();
	
	public StoreImpl(String propertiesPath) throws PersistenceException {
		Properties properties = new Properties();
		try {
			properties.load(this.getClass().getClassLoader().getResourceAsStream(propertiesPath));
		} catch(Exception e) {						// IO, NullPointer
			logger.error(e.getMessage(), e);
			throw new PersistenceException(PersistenceException.CREATE_STORE, propertiesPath, e);
		}
		initialize(properties);
	}

	public StoreImpl (Properties properties) throws PersistenceException {
		initialize(properties);
	}

	/**
	 */
	protected void initialize(Properties properties) throws PersistenceException {
		this.properties = properties;
		cfg = new Configuration();
		Configuration batchCfg = new Configuration();
		
		// Load hibernate properties
		String hibPropsPath = properties.getProperty("mgb.hibernate.properties");
		if (hibPropsPath != null) {
            cfg.addProperties(loadProperties(hibPropsPath));        
            batchCfg.addProperties(loadProperties(hibPropsPath));        
		}

		String mapPropsPath = 	properties.getProperty("mgb.hibernate.mappingfiles");
		if (mapPropsPath != null) {
			Properties mappingFiles = loadProperties(mapPropsPath);
			Enumeration<Object> enumeration = mappingFiles.elements();
			while (enumeration.hasMoreElements()) {
				String className = (String)enumeration.nextElement();
				try {
					Class<?> entityClass = Class.forName(className);
                    cfg = cfg.addClass(entityClass);
                    batchCfg = batchCfg.addClass(entityClass);
                } catch (MappingException e) {
					logger.error(e);
					throw new PersistenceException(PersistenceException.INIT_STORE, "Loading mapping file <" + className + ">", e);		
                } catch (ClassNotFoundException e) {
					logger.error(e);
					throw new PersistenceException(PersistenceException.INIT_STORE, "Loading mapping file <" + className + ">", e);		
                } catch (Exception e) {
					logger.error(e);
					throw new PersistenceException(PersistenceException.INIT_STORE, "Loading mapping file <" + className + ">", e);		
				}
			}
		}	
			
		try {
            sessionFactory = cfg.buildSessionFactory();
            
            batchCfg.setProperty("hibernate.cache.provider_class", "org.hibernate.cache.HashtableCacheProvider");
            batchCfg.setProperty("hibernate.jdbc.batch_size", Integer.toString(SessionImpl.BATCH_SIZE));
            batchSessionFactory = batchCfg.buildSessionFactory();
		} catch (HibernateException e) {
			logger.error(e);       	
			throw new PersistenceException(PersistenceException.INIT_STORE, "buildSessionFactory", e);		
        } catch (Exception e) {
			logger.error(e);
			throw new PersistenceException(PersistenceException.INIT_STORE, "buildSessionFactory", e);		
        }

	}
	
	private Properties loadProperties(String path) throws PersistenceException {
		Properties properties = new Properties();
		try {
			logger.debug("Loading properties from path <" + path + ">");
			System.out.println("Inside storeimpl path ##"+path);
			System.out.println("Load Storeimpl##"+getClass().getClassLoader().getResourceAsStream(path));
			properties.load(getClass().getClassLoader().getResourceAsStream(path));
		} catch(Exception e) {						// IO, NullPointer
			logger.error(e);
			e.printStackTrace();
			throw new PersistenceException(PersistenceException.INIT_STORE, "" + path, e);		
		}
		
		return properties;
	}

	/**
	 * @see de.westlb.mgb.persistence.Store#openSession()
	 */
	@Override
    public Session openSession() throws PersistenceException {
		Session session = null;
		try {
			session = new SessionImpl(this, sessionFactory.openSession(), properties);
			logger.debug("Open  session="+session);
		} catch (Exception e) {								// SQL
			logger.error(e);
			throw new PersistenceException(PersistenceException.OPEN_SESSION, null, e);
		}
		sessions.add(session);
		return session;
	}

	@Override
    public Session openSession(MandantImpl mandant, String ntid) throws PersistenceException {
		Session session = null;
		try {
//            session = new SessionImpl(this, sessionFactory.withOptions().interceptor(
//                  new PersistencyInterceptor(ntid, mandant) ).openSession(), properties);
//            session = new SessionImpl(this, sessionFactory.openSession(
//                    new PersistencyInterceptor(ntid, mandant) ), properties);
            
            session = new SessionImpl(this, sessionFactory.withOptions().interceptor(
                    new PersistencyInterceptor(ntid, mandant) ).openSession(), properties);
			if (mandant==null) {
				logger.debug("Open  session="+session+" for "+ntid);
			} else {
				logger.debug("Open  session="+session+" for "+ntid+" ("+mandant.getCode()+")");				
			}
		} catch (Exception e) {								// SQL
			logger.error(e);
			throw new PersistenceException(PersistenceException.OPEN_SESSION, null, e);
		}
		sessions.add(session);
		return session;
	}
	
    @Override
    public Session openBatchSession() throws PersistenceException {
        Session session = null;
        try {
            session = new SessionImpl(this, batchSessionFactory.openSession(), properties);
            logger.debug("Open  batchSession="+session);
        } catch (Exception e) {                             // SQL
            logger.error(e);
            throw new PersistenceException(PersistenceException.OPEN_SESSION, null, e);
        }
        sessions.add(session);
        return session;
    }

    @Override
    public Session openBatchSession(MandantImpl mandant, String ntid) throws PersistenceException {
        Session session = null;
        try {
//            session = new SessionImpl(this, batchSessionFactory.withOptions().interceptor(
//                    new PersistencyInterceptor(ntid, mandant) ).openSession(), properties);
//            session = new SessionImpl(this, batchSessionFactory.openSession(
//                    new PersistencyInterceptor(ntid, mandant) ), properties);
            session = new SessionImpl(this, sessionFactory.withOptions().interceptor(
                    new PersistencyInterceptor(ntid, mandant) ).openSession(), properties);
            if (mandant==null) {
                logger.debug("Open  batchSession="+session+" for "+ntid);
            } else {
                logger.debug("Open  batchSession="+session+" for "+ntid+" ("+mandant.getCode()+")");             
            }
        } catch (Exception e) {                             // SQL
            logger.error(e);
            throw new PersistenceException(PersistenceException.OPEN_SESSION, null, e);
        }
        sessions.add(session);
        return session;
    }

    /**
	 * Execute all native SQL commands contained as property values
	 * in a property file.
	 * 
	 * @param propertyFilePath Path to the property file
	 * @param doIt if true, execute the SQL commands.
	 * @throws PersistenceException
	 */
	private void executeSqlCmds(String propertyFilePath, boolean doIt) throws PersistenceException {
		org.hibernate.Session session = null;
	
		try {
			session =  sessionFactory.openSession();
		} catch (HibernateException e) {
			throw new PersistenceException(PersistenceException.EXEC_NATIVE_SQL, "Failed to open session", e);		
		}
		
		Properties mappingFiles = loadProperties(propertyFilePath);
		Enumeration<Object> enumeration = mappingFiles.elements();
		while (enumeration.hasMoreElements()) {
			String sqlCmd = (String)enumeration.nextElement();
			try {
				logger.debug(sqlCmd);
				if (doIt) {
//					Query query = session.createSQLQuery(sqlCmd,new String[] {}, new Class[] {}); //hibernate2
					Query query = session.createSQLQuery(sqlCmd);
					query.list();
				}
			} catch (Exception e) {
				try {session.close();
				} catch (Exception e1) {
				}
				logger.error(e);
				throw new PersistenceException(PersistenceException.EXEC_NATIVE_SQL, "Execute native sql cmd", e);		
			}
		}
		
		try {session.close();
		} catch (Exception e1) {
		}
	}
	
	/**
	 * Exports the database schema based on the hibernate mapping files. Additionally 
	 * a set of SQL's from a property file is loaded.  
	 * 
	 * @param export if true, the script is executed on database.
	 * 
	 */
	public void exportSchema(boolean export) throws PersistenceException {
		try {
			boolean script = true;
			SchemaExport schemaExport = new SchemaExport();
			schemaExport.setOutputFile(null);
			schemaExport.setDelimiter(null);
			//schemaExport.drop(script, export);
			//schemaExport.create(script, export);
			executeSqlCmds("postSchemaExportCmds.properties", true);
		} catch (HibernateException e) {
			logger.error("Schema export failed!", e);
			throw new PersistenceException("Schema Export failed!");
		}
	}

	protected void closeSession(Session session) {
		logger.debug("Close session="+session);
		sessions.remove(session);
	}
	
	/**
	 * Reads the disk cache path from the OSCache property file.
	 */
	private String getDiskCachePath() {
		Properties properties = new Properties();
		try {
			properties.load(this.getClass().getClassLoader().getResourceAsStream(OS_CACHE_PROPERTIES));
		} catch(Exception e) {						// IO, NullPointer
			logger.error("Can not open oscache.properties");
			return null;
		}
		
		return properties.getProperty("cache.path");
	}

	/**
	 * Deletes the OSCache disk cache. Because there is no API, this is done "manually".
	 *
	 */
	public void clearDiskCache() {
		String dirPath = getDiskCachePath();
		logger.info("Clearing disk cache <" + dirPath + ">");
		if (dirPath != null) {
			dirPath = dirPath + File.separatorChar + "application";
			File dir = new File(dirPath);
			if (!(dir.exists())) {
				logger.info("Directory does not exist.");
				return;
			}
			
			boolean done = false;
			while (!done) {
				FileFilter filter = new FileFilter() {
					int count = 0;
					@Override
                    public boolean accept(File file) {
						if (file.isFile() && file.getName().endsWith(".cache")){
							if (count < 5000) {
								count++;
								return true; 
							}
						}
						 return false;
					 }
				};
				
				done = true;
				File[] files = dir.listFiles(filter);
				for (int i = 0; i < files.length; i++) {
					if (files[i].delete()) {
						done = false;
					}
				}
			}
		}
	}
	
	/**
	 * Clears Hibernates second level object cache. Also deletes the OSCache disk cache.
	 */
	@Override
    public void clearCache() {
		try {
		    long startMillis = System.currentTimeMillis();
			clearDiskCache();
			/*
			 * Iterator<PersistentClass> iterator = cfg.getClassMappings(); while
			 * (iterator.hasNext()) { PersistentClass persistentClass = iterator.next();
			 * sessionFactory.getCache().evictEntityRegion(persistentClass.getMappedClass())
			 * ; batchSessionFactory.getCache().evictEntityRegion(persistentClass.
			 * getMappedClass()); }
			 */
            logger.info("Clearing cache in "+(System.currentTimeMillis()-startMillis)+" millis");
		} catch (HibernateException e) {
			logger.error("Exception clearing object cache", e);
		}		
	}

    @Override
    public void clearBatchCache() {
        try {
			/*
			 * Iterator<PersistentClass> iterator = cfg.getClassMappings(); while
			 * (iterator.hasNext()) { PersistentClass persistentClass = iterator.next();
			 * batchSessionFactory.getCache().evictEntityRegion(persistentClass.
			 * getMappedClass()); }
			 */
        } catch (HibernateException e) {
            logger.error("Exception clearing object cache", e);
        }       
    }
	
	@Override
    public void close() throws PersistenceException {
		try {
			if (sessionFactory != null && !sessionFactory.isClosed()) {
				logger.debug("closing session factory");
				sessionFactory.close();
			} else {
				logger.debug("session factory already closed");
			}
            if (batchSessionFactory != null && !batchSessionFactory.isClosed()) {
                logger.debug("closing batch session factory");
                batchSessionFactory.close();
            } else {
                logger.debug("batch session factory already closed");
            }
		} catch (HibernateException e) {
			logger.error("Exception closing sessionFactory", e);
		}		
	}
}

package de.westlb.mgb.persistence;

import java.util.Properties;

import org.apache.log4j.Logger;

import de.westlb.mgb.persistence.hibernate.StoreImpl;

/**
The Singleton defines an Instance operation that lets clients access its unique instance.  It may be responsible for creating its own unique
instance.
 * @modelguid {87A5D389-F2D6-4EC9-9722-A94C69DAD714}
*/
public class StoreSingleton {
	
	private static Logger logger = Logger.getLogger(StoreSingleton.class);
	
	/**
	This attribute stores the instance of the Singleton class.
	 * @modelguid {8D6F4FE6-A532-4209-8ADF-A20251E49413}
	*/
	private static Store uniqueInstance;
	private static Properties properties  = null;

	/**
	This operation implements the logic for returning the same instance of the Singleton pattern.
	 * @modelguid {E2366FB2-968B-415D-9521-DA4CAB07F2E0}
	*/
	public static synchronized Store getUniqueInstance() throws PersistenceException {
		if (uniqueInstance == null) {
			if (properties == null) {
				setProperties("server.properties");
			}
			uniqueInstance = new StoreImpl(getProperties());
		}
		return uniqueInstance; 
		
	}

	public static void close() throws PersistenceException {
		if (uniqueInstance != null) {
			uniqueInstance.close();
		}
	}

    /**
     * Sets the storePropertiesPath.
     * @param storePropertiesPath The storePropertiesPath to set
     */
    public static void setProperties(String path) throws PersistenceException {
		properties = new Properties();
		System.out.println("##Path insideStoresingle --"+path);
		try {
			logger.debug("properties url: "+StoreSingleton.class.getClassLoader().getResource(path).toString());
			System.out.println("##Load##"+StoreSingleton.class.getClassLoader().getResourceAsStream(path));
			properties.load(StoreSingleton.class.getClassLoader().getResourceAsStream(path));
		} catch(Exception e) {	
			// IO, NullPointer
			e.printStackTrace();
			throw new PersistenceException(PersistenceException.CREATE_STORE, path, e);
		}    
	}

    /**
     * Returns the properties.
     * @return Properties
     */
    public static Properties getProperties() {
        return properties;
    }

    /**
     * Sets the properties.
     * @param properties The properties to set
     */
    public static void setProperties(Properties properties) {
        StoreSingleton.properties = properties;
    }

}


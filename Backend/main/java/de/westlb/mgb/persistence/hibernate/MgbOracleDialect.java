package de.westlb.mgb.persistence.hibernate;

import java.sql.Types;

import org.hibernate.dialect.Oracle10gDialect;

/**A variant of {@link Oracle10gDialect} that maps <code>TIMESTAMP</code>
 * to SQL <code>DATE</code>. MGB currently has problems with <code>TIMESTAMP</code>
 * columns &ndash; the SQLLDR control files cannot handle them. */
public class MgbOracleDialect
extends Oracle10gDialect
{
    
    protected void registerDateTimeTypeMappings()
    {
        registerColumnType(Types.DATE,"date");
        registerColumnType(Types.TIME,"date");
        registerColumnType(Types.TIMESTAMP,"date");
    }

}

package org.evm.core.service;

import java.sql.SQLException;
import java.util.List;

import org.evm.core.exception.SmartDBAccessException;
import org.evm.core.log.DataCenterLogger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapExecutor;

public class BaseDBService extends SqlMapClientDaoSupport {
	protected DataCenterLogger logger=new DataCenterLogger(this.getClass());
	public BaseDBService() {
	}

	protected int getCount(String findSqlName, Object whereCause) {
		try {
			int totalCount = ((Integer) getSqlMapClientTemplate().queryForObject(findSqlName, whereCause)).intValue();
			return totalCount;
		} catch (Exception e) {
			logger.error(e);
			throw new SmartDBAccessException(e);
		}
	}

	protected void saveOrUpdate(String insertSqlName, String updateSqlName, Object val) {
		try {
			int rtnVal = getSqlMapClientTemplate().update(updateSqlName, val);
			if (rtnVal == 0)
				getSqlMapClientTemplate().insert(insertSqlName, val);
		} catch (Exception e) {
			logger.error(e);
			throw new SmartDBAccessException(e);
		}
	}

	/**
	 * 查询
	 * 
	 * @param findSqlName
	 * @param whereCause
	 * @return
	 */
	protected List find(String findSqlName, Object whereCause) {
		try {
			return getSqlMapClientTemplate().queryForList(findSqlName, whereCause);
		} catch (Exception e) {
			logger.error(e);
			throw new SmartDBAccessException(e);
		}
	}

	/**
	 * 查询
	 * 
	 * @param getSqlName
	 * @param whereCause
	 * @return
	 */
	protected Object get(String getSqlName, Object whereCause) {
		try {
			return getSqlMapClientTemplate().queryForObject(getSqlName, whereCause);
		} catch (Exception e) {
			logger.error(e);
			throw new SmartDBAccessException(e);
		}
	}

	/**
	 * 插入
	 * 
	 * @param insertSqlName
	 * @param insertVal
	 * @return
	 */
	protected Object insert(String insertSqlName, Object insertVal) {
		try {
			return getSqlMapClientTemplate().insert(insertSqlName, insertVal);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			throw new SmartDBAccessException(e);
		}
	}

	/**
	 * 更新
	 * 
	 * @param updateSqlName
	 * @param updateVal
	 * @return
	 */
	protected int update(String updateSqlName, Object updateVal) {
		try {
			return getSqlMapClientTemplate().update(updateSqlName, updateVal);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			throw new SmartDBAccessException(e);
		}
	}

	/**
	 * 删除
	 * 
	 * @param deleteSqlName
	 * @param whereCause
	 * @return
	 */
	protected int delete(String deleteSqlName, Object whereCause) {
		try {
			return getSqlMapClientTemplate().delete(deleteSqlName, whereCause);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			throw new SmartDBAccessException(e);
		}
	}

	/**
	 * 批量插入
	 * 
	 * @param insertSqlName
	 * @param insertVal
	 * @return
	 */
	protected int batchInsert(final String insertSqlName, final List insertVal) {
		try {
			Object updateCnt = getSqlMapClientTemplate().execute(new SqlMapClientCallback() {

				public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
					executor.startBatch();
					for (int i = 0; i < insertVal.size(); i++) {
						executor.insert(insertSqlName, insertVal.get(i));
					}
					int updCount = executor.executeBatch();
					return Integer.valueOf(updCount);
				}

			});
			return ((Integer) updateCnt).intValue();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
			throw new SmartDBAccessException(e);
		}

	}

	/**
	 * 批量更新
	 * 
	 * @param updateSqlName
	 * @param updateVal
	 * @return
	 */
	protected int batchUpdate(final String updateSqlName, final List updateVal) {
		try {
			Object updateCnt = getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
					executor.startBatch();
					for (int i = 0; i < updateVal.size(); i++) {
						executor.update(updateSqlName, updateVal.get(i));
					}
					int updCount = executor.executeBatch();
					return Integer.valueOf(updCount);
				}
			});
			return ((Integer) updateCnt).intValue();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
			throw new SmartDBAccessException(e);
		}
	}

	/**
	 * 批量删除
	 * 
	 * @param deleteSqlName
	 * @param deleteVal
	 * @return
	 */
	protected int batchDelete(final String deleteSqlName, final List deleteVal) {
		try {
			Object updateCnt = getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
					executor.startBatch();
					for (int i = 0; i < deleteVal.size(); i++) {
						executor.delete(deleteSqlName, deleteVal.get(i));
					}
					int updCount = executor.executeBatch();
					return Integer.valueOf(updCount);
				}
			});
			return ((Integer) updateCnt).intValue();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e);
			throw new SmartDBAccessException(e);
		}
	}
}
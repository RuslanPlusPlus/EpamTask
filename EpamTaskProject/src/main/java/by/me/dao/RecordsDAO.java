package by.me.dao;

import java.util.List;

import by.me.daoImpl.Record;


public interface RecordsDAO {

	public List<Record> getRecords();
	public void add(Record record);
	public void update(Record record, int id);
	public void deleteAll();
}

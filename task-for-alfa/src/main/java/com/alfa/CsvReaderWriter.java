package com.alfa;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
import com.alfa.model.ClientBalance;
import com.alfa.model.Report;
import com.alfa.model.Transaction;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CsvReaderWriter {

    String[] mapping1 = new String[]{"id", "asset", "assetNumber"};
    String[] mapping2 = new String[]{"transaction_id", "client_id", "type", "asset", "count"};
    CsvToBean csv = new CsvToBean();

    public List readClientBalance(String path1, String path2) throws IOException {
        CSVReader csvReader1 = new CSVReader(new FileReader(path1), ',', '"', 1);
        CSVReader csvReader2 = new CSVReader(new FileReader(path2), ',', '"', 1);
        ColumnPositionMappingStrategy strategy1 = new ColumnPositionMappingStrategy();
        strategy1.setType(ClientBalance.class);
        strategy1.setColumnMapping(mapping1);
        List list1 = csv.parse(strategy1, csvReader1);

        List<ClientBalance> clientBalanceList = new LinkedList<>();
        for (Object object : list1) {
            ClientBalance clientBalance = (ClientBalance) object;
            clientBalanceList.add(clientBalance);
        }

        ColumnPositionMappingStrategy strategy2 = new ColumnPositionMappingStrategy();
        strategy2.setType(Transaction.class);
        strategy2.setColumnMapping(mapping2);
        List list2 = csv.parse(strategy2, csvReader2);
        List<Transaction> transactionList = new LinkedList<>();
        for (Object object : list2) {
            Transaction transaction = (Transaction) object;
            transactionList.add(transaction);
        }
        List result = new ArrayList<>();
        result.add(clientBalanceList);
        result.add(transactionList);
        return result;
    }

    public void createCSV(List list, String path3, String path4) throws IOException {
        List<ClientBalance> arrClientBalance = (List<ClientBalance>) list.get(0);
        List<Report> reports = (List<Report>) list.get(1);
        CSVWriter writer1 = new CSVWriter(new FileWriter(path3, true));
        String[] strings;
        writer1.writeNext("client_id,asset,number of assets".split(","));
        for (ClientBalance clientBalance: arrClientBalance) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(clientBalance.getId()+",");
            stringBuilder.append(clientBalance.getAsset()+",");
            stringBuilder.append(clientBalance.getAssetNumber()+",");
            strings = stringBuilder.toString().split(",");
            writer1.writeNext(strings);
        }
        CSVWriter writer2 = new CSVWriter(new FileWriter(path4, true));
        writer2.writeNext("report_id,transaction_sale_id,transaction_buy_id,number of assets".split(","));
        for (Report report: reports) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(report.getReport_id()+",");
            stringBuilder.append(report.getTransactSale_id()+",");
            stringBuilder.append(report.getTransactBuy_id()+",");
            stringBuilder.append(report.getAssetCount()+",");
            strings = stringBuilder.toString().split(",");
            writer2.writeNext(strings);
        }
        writer1.close();
        writer2.close();
    }
}

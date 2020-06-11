package com.alfa;

import com.alfa.model.ClientBalance;
import com.alfa.model.Report;
import com.alfa.model.Transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        String path1 = "src/main/resources/clientBalance.csv";  //тестовый файл1
        String path2 = "src/main/resources/transactions.csv";   //тестовый файл2

        String path3 = "src/main/resources/result/clientBalanceResult.csv";
        String path4 = "src/main/resources/result/report.csv";


        CsvReaderWriter csvReaderWriter = new CsvReaderWriter();
        List list = csvReaderWriter.readClientBalance(path1, path2);
        List<ClientBalance> clientBalanceList = (List<ClientBalance>) list.get(0);
        List<Transaction> transactionList = (List<Transaction>) list.get(1);

        List outputDataForCsv = new ArrayList();
        List<Report> reports = new LinkedList<>();

        List<ClientBalance> clientBalanceCopy = new LinkedList<>();
        for (ClientBalance temp: clientBalanceList) {
            ClientBalance clientBalance = new ClientBalance();
            clientBalance.setId(temp.getId());
            clientBalance.setAsset(temp.getAsset());
            clientBalance.setAssetNumber(temp.getAssetNumber());
            clientBalanceCopy.add(clientBalance);
        }

        Report report;

        try {
            for (int i = 0; i < transactionList.size(); i++) {
                String clientassets1 = null;

                for (int j = 0; j < clientBalanceList.size(); j++) {

                    if (transactionList.get(i).getClient_id().equals(clientBalanceList.get(j).getId())) {
                        clientassets1 = clientBalanceList.get(j).getAssetNumber();
                        clientBalanceCopy.get(j).setAssetNumber("0");
                    }
                    if (transactionList.get(i+1).getClient_id().equals(clientBalanceList.get(j).getId())){
                        clientBalanceCopy.get(j).setAssetNumber(""+(Integer.parseInt(clientBalanceCopy.get(j)
                                .getAssetNumber())+Integer.parseInt(transactionList.get(i+1).getCount())));
                    }
                }

                if (!clientassets1.equals(transactionList.get(i+1).getCount())) {
                    throw new Exception(); //первое и третье ограничение
                }
                if (Integer.parseInt(clientassets1) <0) {
                    throw new Exception(); //четвертое ограничение
                }
                report = new Report(""+i, transactionList.get(i).getTransaction_id(),
                        transactionList.get(i+1).getTransaction_id(), transactionList.get(i).getCount());
                reports.add(report);
                i++;
            }
            outputDataForCsv.add(clientBalanceCopy);
            outputDataForCsv.add(reports);

            csvReaderWriter.createCSV(outputDataForCsv, path3, path4);
        } catch (Exception e) {
            System.out.println("invalid input files.csv");
        }

    }
}

package ru.sapteh;

import ru.sapteh.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException {
        Connection connection = DataBaseConnection.getConnection();

        List<Configuration> configurations = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Просмотр данных из бд -s: ");
        System.out.println("Добавить данные в бд -i: ");
        System.out.println("Обновить данные из бд -u: ");
        System.out.println("Удалить данные из бд -d: ");


        String strReader = reader.readLine();
        if (strReader.equals("-s")){
            String sql = String.format("SELECT * FROM " + Configuration.TABLE_NAME);
            try {
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()){
                    Long id = resultSet.getLong(Configuration.ID_COLUMN);
                    String model= resultSet.getString(Configuration.MODELCPU_COLUMN);
                    String ram = resultSet.getString("ram");
                    String hdd = resultSet.getString("hdd");
                    String powerSupply = resultSet.getString("powerSupply");
                    Configuration configuration= new Configuration(id,model,ram,hdd,powerSupply);
                    configurations.add(configuration);

                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            System.out.println(configurations);
        }
        if (strReader.equals("-i")){
            System.out.println("input modelCPU: ");
            String model = reader.readLine();
            System.out.println("input ram : ");
            String ram = reader.readLine();
            System.out.println("input hdd: ");
            String hdd = reader.readLine();
            System.out.println("input powerSupply: ");
            String powerSupply = reader.readLine();

            Configuration configuration = new Configuration((long) (configurations.size() + 1),model,ram,hdd,powerSupply);
            try {
                String sql = String.format("INSERT INTO  %s (%s, %s, %s, %s) VALUES (?, ?, ?, ?)", Configuration.TABLE_NAME,Configuration.MODELCPU_COLUMN, Configuration.RAM_COLUMN, Configuration.HDD_COLUMN,Configuration.POWERSUPPLY_COLUMN );
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString( 1,configuration.getModelCPU());
                statement.setString( 2,configuration.getRam());
                statement.setString( 3,configuration.getHdd());
                statement.setString( 4,configuration.getPowerSupply());
                statement.executeUpdate();
                configurations.add(configuration);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        if (strReader.equals("-u")){
            System.out.println("input id: ");
            Long id = Long.parseLong(reader.readLine());
            System.out.println("input modelCPU: ");
            String model = reader.readLine();
            System.out.println("input ram : ");
            String ram = reader.readLine();
            System.out.println("input hdd: ");
            String hdd = reader.readLine();
            System.out.println("input powerSupply: ");
            String powerSupply = reader.readLine();

            Configuration configuration = new Configuration(id,model,ram,hdd,powerSupply);
            try {
                String sql = String.format("UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ? WHERE %s = ?",
                        Configuration.TABLE_NAME,
                        Configuration.MODELCPU_COLUMN,
                        Configuration.RAM_COLUMN,
                        Configuration.HDD_COLUMN,
                        Configuration.POWERSUPPLY_COLUMN,
                        Configuration.ID_COLUMN );

                PreparedStatement statement = connection.prepareStatement(sql);

                statement.setString( 1,configuration.getModelCPU());
                statement.setString( 2,configuration.getRam());
                statement.setString( 3,configuration.getHdd());
                statement.setString( 4,configuration.getPowerSupply());
                statement.setLong(5,configuration.getId());

                statement.executeUpdate();
                configurations.add(configuration);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (strReader.equals("-d")){
            System.out.println("input id: ");
            Long id = Long.parseLong(reader.readLine());

            try {
                String sqlDel = String.format("DELETE FROM %s WHERE %s = ?", Configuration.TABLE_NAME,Configuration.ID_COLUMN);
                PreparedStatement statement = connection.prepareStatement(sqlDel);
                statement.setLong(1,id);
                statement.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}



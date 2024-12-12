package saga1;

import java.sql.*;
import java.util.*;
 class Menu{
    Connection cn;
    static Scanner scan =new Scanner(System.in);
    PreparedStatement ps;
    Statement st;
// function to make connection with database
    public void connect() throws SQLException,ClassNotFoundException{
        final String url="jdbc:mysql://localhost:3306/Ncit";
        final String uname="root";
        final String pwd="";
        Class.forName("com.mysql.cj.jdbc.Driver");
        cn=DriverManager.getConnection(url, uname, pwd);
        System.out.println("Database connected");

    }
    public void show() throws SQLException{
st=cn.createStatement();
String q= "Select * from Student";
ResultSet rs= st.executeQuery(q);
while (rs.next()) {
    System.out.println(" Id= "+rs.getInt(1) +" Name= "+rs.getString(2)+ " Marks= "+rs.getInt(3) +" Faculty= "+rs.getString(4));
    
}

    }
    public void entry() throws  SQLException{
    	PreparedStatement ps=cn.prepareStatement("Insert into student values(?,?,?,?)");
    	
        do{
            System.out.println(" Enter Id");
            int id= scan.nextInt();
            System.out.println(" Enter Name");
            String name= scan.next();
            System.out.println(" Enter Marks");
            int marks= scan.nextInt();
            System.out.println(" Enter Faculty");
            String faculty= scan.next();

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, marks);
            ps.setString(4, faculty);

            int i=ps.executeUpdate();
            System.out.println(i+" Records added ");
            System.out.println("Enter any key to continue ");
            System.out.println("Enter N/n to exit");
      
            }while(!scan.next().toLowerCase().startsWith("n"));

        }
        public void exit()throws SQLException{
            cn.close();
            System.exit(0);
        }
        public void searchByName()throws SQLException{
            ps=cn.prepareStatement("select * from student where name=?");
            System.out.println("Enter Name");
            String name= scan.next();
            ps.setString(2, name);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1)+" " +rs.getString(2) +" "+rs.getInt(3)+" "+rs.getString(4));

                
            }
        }
        public void deleteById()throws SQLException{
            ps=cn.prepareStatement("delete from student where id=?");
            System.out.println("Enter Id");
            int  id= scan.nextInt();
            ps.setInt(1, id);
            int  r =ps.executeUpdate();
            System.out.println(r+"Records deleted ");


        }
        public void showMenu(){
            System.out.println("*****Menu*****");
            System.out.println("1.show Records");
            System.out.println("2.Add Records");
            System.out.println("3.Search By Name");
            System.out.println("4.Delete BY Id");
            System.out.println("5 Exit");
            System.out.println("****************");
        }
        public static void main(String[] args)  throws Exception{
            Menu m=new Menu();
            m.connect();
            m.showMenu();
            while (true) {
                System.out.println("Enter choice");
                int choice=scan.nextInt();
                switch(choice){
                    case 1:
                    m.show();
               break;
               case 2:
               m.entry();
               break;
               case 3:
               m.searchByName();
               break;
               case 4:
               m.deleteById();
               break;
               case 5:
               m.exit();
               default:
               {
                m.showMenu();
               }

                }

                
            }

            
        }
    }

    
 




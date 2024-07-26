#Compile maven project
mvn clean compile

#Run the main class
mvn exec:java -Dexec.mainClass="com.example.Main"
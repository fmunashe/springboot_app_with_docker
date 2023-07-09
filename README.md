# springboot_app_with_docker
## To run the project locally, follow the steps below ##
- Make sure you have docker desktop installed and running on the machine
- Clone the project to a directory of your choice
- Navigate to the project root folder and run docker-compose up --build
  ### The above will create three images for the mysql db, phpmyadmin and the application itself ###
  - Access the db via phpmyadmin on the browser on localhost:8888 and user host as mysqldb, user as db_user and password as password
  - Access the java application on the usual localhost:8080

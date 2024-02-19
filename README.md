Technical Stack

### :rocket: Backend:
- Hosted on AWS using Elastic Beanstalk
- Hosted database on AWS using RDS with MySQL
- Spring Boot
- Maven
- API with endpoints for CRUD operations on Yarn objects
- JUnit tests
- Cross-origin support in controllers for frontend calls

### :repeat: CI/CD:
- CI with GitHub Actions workflow that runs build and tests on push or pull request to main

<img src="images/ci/Screenshot 2024-02-19 135224.png" alt="Alt Text" style="width: 480px; height: 360px;">

- CD with AWS CodePipeline that retrieves code from the repo, builds it, stores it in an S3 bucket (provided by EB), and deploys it to Elastic Beanstalk on port 5000

<img src="images/cd/Screenshot 2024-02-18 185248.png" alt="Alt Text" style="width: 150px; height: 450px;">

### :link: Frontend 
**[click link to go to front-end repository](https://github.com/EllenHalv/React-frontend-YarnApp)**
* Runs locally on port 3000
* JavaScript
* HTML
* React
* Axios
* Bootstrap
* Has a simple UI to perform CRUD operations
* Data is saved to the database and displayed in a list in the UI

### :computer: We can test the endpoints in Postman using Swagger...

Visit Swagger UI to get an overview of the endpoints: [http://yarn-app-env-1.eba-bvcapd7u.us-east-1.elasticbeanstalk.com//swagger-ui.html](http://yarn-app-env-1.eba-bvcapd7u.us-east-1.elasticbeanstalk.com//swagger-ui.html)

<img src="images/Screenshot 2024-02-18 202624.png" alt="Alt Text" style="width: 650px; height: 250px;">

### :rocket: ...Or start the frontend locally

**Prerequisites:**
* Node.js and npm

**Steps:**

1. Clone the repository:
   ```bash
   git clone [https://github.com/EllenHalv/React-frontend-YarnApp/ems-frontend.git](https://github.com/EllenHalv/React-frontend-YarnApp/ems-frontend.git)
   
    cd ems-frontend
    ```
2. Install the dependencies:
```bash
   npm install
   ```
3. Run the project (runs in the development server):
   ```bash
    npm run dev
    ```
4. Open your browser and go to:
[http://localhost:3000](http://localhost:3000)

<img src="images/front-end/Screenshot 2024-02-19 142904.png" alt="Alt Text" style="width: 650px; height: 280px;">

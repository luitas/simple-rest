node {

    stage('Clone repository') {
        checkout scm
    }
    
   
    def app

    stage('Build image') {
        app = docker.build("mif/user-rest")
    }
    
    stage('Push image') {
        docker.withRegistry('http://193.219.91.103:8081/repository/docker-private/') {
            app.push("${env.BUILD_NUMBER}")
            app.push("latest")
        }
    }
    
}

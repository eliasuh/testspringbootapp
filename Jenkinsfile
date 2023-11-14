pipeline {
    agent any
    environment {
          PATH = "/opt/apache-maven-3.8.6/bin:$PATH"      
          DOCKERHUB_CREDENTIALS = credentials('dockerhub')   
    }
    triggers {
        pollSCM "* * * * *"
    }
    stages {
        stage("clone code with git") {
            steps {
              checkout([$class: "GitSCM", 
                branches: [[name: "*/devops"]],
                extensions: [],
                userRemoteConfigs: [[url: "https://github.com/eliasuh/testspringbootapp.git"]]])
              
          }
        }
        stage("build code with maven") {
           steps {  
                sh "mvn -version"
                sh "mvn clean install -DskipTests"
          }
        }
        stage('Staging') {
            steps {    
                script{
                 withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
                    // some block
                    sh "docker login -u uhelias -p $dockerhubpwd"
                    echo "Login Completed" 
                   // sh 'sudo docker-compose build"
                   // sh 'sudo docker-compose up -d'
                 }   
              }
             
            }
        }
    }
}
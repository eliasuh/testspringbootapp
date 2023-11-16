#!groovy
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
               // sh "mvn clean install"
          }
        }
        stage('docker login') {
          steps {
            sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
           // sh "docker-compose build"
            sh "docker-compose push"
            sh "docker-compose up -d"
          //  sh "docker-compose down"
          //  sh "docker-compose rm -f"
           // sh "docker rmi -f mysql:5.7"
          //  sh "docker rmi -f redis:4.0"
          }
        }
    }
}
pipeline {
    agent any
    environment {
          PATH = /opt/apache-maven-3.8.6/bin:$PATH
    }
    stages {
        stage("git") {
            steps {
              checkout([$class: "GitSCM", 
                branches: [[name: "*/devops"]],
                extensions: [],
                userRemoteConfigs: [[url: "https://github.com/eliasuh/testspringbootapp.git"]]])
              
          }
        }
        stage("build") {
           steps {            
                sh "mvn -version"
                sh "mvn clean install"
          }
        }
    }
}
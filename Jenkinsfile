pipeline {
    agent any
    tools {
        maven 'Maven 3.8.6'
    }
    stages {
        stage("Build") {
            steps {
              checkout([$class: "GitSCM", 
                branches: [[name: "*/devops"]],
                extensions: [],
                userRemoteConfigs: [[url: "https://github.com/eliasuh/testspringbootapp.git"]]])
                sh 'mvn clean install'
          }
        }
    }
}
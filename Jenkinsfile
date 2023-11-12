pipeline {
    agent any
    stages {
        stage("Build") {
            steps {
              checkout([$class: "GitSCM", 
                branches: [[name: "*/devops"]],
                extensions: [],
                userRemoteConfigs: [[url: "https://github.com/eliasuh/testspringbootapp.git"]]])
                sh 'mvn -version'
          }
        }
    }
}
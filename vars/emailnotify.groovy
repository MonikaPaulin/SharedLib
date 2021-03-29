void call() {
emailext(       attachLog: true, attachmentsPattern: 'Release_Status.csv',
                subject: "${currentBuild.result}: ${env.JOB_NAME} [${env.BUILD_NUMBER}]",
                to: 'MonikaPaulin.Jeyakumar@cognizant.com',
                recipientProviders: [[$class: 'CulpritsRecipientProvider']],
                body: "PFA"
            )
}

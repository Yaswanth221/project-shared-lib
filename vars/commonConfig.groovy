def call() {
  return [
    maven: [
      // Put settings.xml inside demo-app-jen repo root
      settings_xml: "settings.xml",
      user: "jenkins-bot",
      email: "jenkins-bot@example.com"
    ],
    git_ssh_credentials: "local-ssh-creds",

    // Toggle these to see behavior
    secret_scan_enable: true,
    sca_scan_enable: true
  ]
}

RosterIngester

TODO: Documentation, Setup, Testing

## Setup
1. Copy the env file and setup the config.
 ```
 cp resources/example.env.yaml resources/env.yaml
 ```
Replace with scarlet DB credentials:
```concept
url: localhost
database: master
username: foo
password: foo
port: 1433
```
Setup SmartyStreet credentials
```concept
smartyAuthId: foo
smartyAuthToken: bar
proxyServer: proxyServer
proxyPort: "1234"
```

### Setup DB
2. Download the JDBC Driver https://www.microsoft.com/en-us/download/details.aspx?id=11774
and copy the sqljdbc_auth.dll to C:\Program Files\Java\jre_Version\bin

## Formal Testing
Testing is done on three real Rosters.
(TODO)

## Production
(TODO)

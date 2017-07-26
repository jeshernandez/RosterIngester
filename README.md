RosterIngester

## Setup
1. Copy the env file and setup the config files. The env file presets the roster directory. The server file presets the MSSQL, Oracle, DB2 and other connections.
 ```bash
 cp resources/example.env.yaml resources/env.yaml
 cp resources/example.servers.yaml resources/servers.yaml
 ```

### Setup DB
2. Download the JDBC Driver https://www.microsoft.com/en-us/download/details.aspx?id=11774
and copy the sqljdbc_auth.dll to C:\Program Files\Java\jre_Version\bin

## Formal Testing
Unit tests are in RosterIngester/src/tests.

Development Testing: three real Rosters in the (TODO) folder.
(TODO)

## Production
(TODO)

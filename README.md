# README

## NOTES

### JARKATA EE BASICS

https://www.youtube.com/watch?v=BgmFbKlD_OI&list=PLFMhxiCgmMR-wxd1BAFiSGdDGROxRgDDX

### PAYARA MICRO

https://www.youtube.com/watch?v=BgmFbKlD_OI&list=PLFMhxiCgmMR-wxd1BAFiSGdDGROxRgDDX

https://www.youtube.com/watch?v=kXrL32drBHE

https://www.payara.fish/downloads/payara-platform-community-edition/

    java -jar ./payara-micro-<version>.jar

Specify port.

    java -jar ./payara-micro-<version>.jar --port <port>

Find next free port with `--autobindhttp`.

    java -jar ./payara-micro-<version>.jar --autobindhttp

Deploy application.

    java -jar ./payara-micro-<version>.jar --autobindhttp --deploy ./<application>.war

Deploy scripts are provided in the projects, run `./deploy.sh`.


### CURL

    curl --header "Content-Type: application/json" \
    --request POST \
    --data '{"strasse":"test","hausnummer": 22, "plz": 123456, "stadt": "test"}' \
    http://localhost:8083/muellservice/data/muell/create

    curl --header "Content-Type: application/json" \
    --request POST \
    --data '{"username":"test","password": "test"}' \
    http://localhost:8081/authservice/data/auth/login


## TODO

* Cleanup packages
* UI

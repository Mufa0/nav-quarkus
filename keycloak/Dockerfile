FROM quay.io/keycloak/keycloak:12.0.4
COPY realm-export.json /tmp/realm-export.json

ENV KEYCLOAK_USER admin
ENV KEYCLOAK_PASSWORD admin

ENV KEYCLOAK_IMPORT "/tmp/realm-export.json -Dkeycloak.profile.feature.upload_scripts=enabled"
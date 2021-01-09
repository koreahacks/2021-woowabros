# react build
FROM node:12.20.1-alpine3.10 as builder

RUN mkdir /app
WORKDIR /app
ENV PATH /app/node_modules/.bin:$PATH
COPY frontend/package.json /app/package.json
RUN npm install --silent

COPY frontend /app
RUN ls -a /app/public
RUN npm run build

# nginx
FROM nginx:alpine
COPY infra/default.conf /etc/nginx/conf.d/default.conf
COPY --from=builder /app/build /usr/share/nginx/html

RUN apk add certbot certbot-nginx
RUN mkdir /etc/letsencrypt


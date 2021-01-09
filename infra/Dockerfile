FROM nginx:alpine
COPY default.conf /etc/nginx/conf.d/default.conf
RUN apk add certbot certbot-nginx
RUN mkdir /etc/letsencrypt

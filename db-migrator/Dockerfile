FROM php:8.3.29-cli-trixie

COPY --from=composer:latest /usr/bin/composer /usr/bin/composer

WORKDIR /var/www/html

RUN apt-get update && apt-get install -y locales tzdata \
    && sed -i '/ko_KR.UTF-8/s/^# //g' /etc/locale.gen && locale-gen \
    && ln -snf /usr/share/zoneinfo/Asia/Seoul /etc/localtime && echo "Asia/Seoul" > /etc/timezone

ENV LANG=ko_KR.UTF-8
ENV LC_ALL=ko_KR.UTF-8
ENV TZ=Asia/Seoul

RUN apt-get install -y \
    git \
    curl \
    libpng-dev \
    libonig-dev \
    libxml2-dev \
    zip \
    unzip \
    && apt-get clean && rm -rf /var/lib/apt/lists/*
RUN docker-php-ext-install pdo_mysql mbstring exif bcmath
RUN composer create-project laravel/laravel:^12.0 . --no-interaction
RUN chown -R www-data:www-data /var/www

USER www-data

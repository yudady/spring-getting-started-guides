# Note: You can use any Debian/Ubuntu based image you want. 
FROM mcr.microsoft.com/devcontainers/base:ubuntu

# USER ROOT

RUN set -ex; \ 
    apt-get update; \
    apt-get install -y sudo; \
    apt-get install -y  --no-install-recommends \
    #        apt-utils dialog 
    zip \
    unzip \
    bash-completion \
    build-essential \
    htop \
    jq \
    less \
    locales \
    man-db \
    software-properties-common \
    sudo \
    time \
    vim \
    multitail \
    lsof \
    openssh-client iproute2 lsb-release; 

#######
RUN curl -fsSL "https://get.sdkman.io" | bash; \
    bash -c ". /root/.sdkman/bin/sdkman-init.sh \
    && sdk install java 22.3.r17-grl \
    && sdk install maven \
    && sdk flush archives \
    && sdk flush temp \
    && mkdir /root/.m2 \
    && mkdir /root/.bashrc.d \
    && echo 'export SDKMAN_DIR=\"/root/.sdkman\"' >> /root/.bashrc.d/99-java \
    && echo '[[ -s \"/root/.sdkman/bin/sdkman-init.sh\" ]] && source \"/root/.sdkman/bin/sdkman-init.sh\"' >> /root/.bashrc.d/99-java"; \
    # above, we are adding the sdkman init to .bashrc (executing sdkman-init.sh does that), because one is executed on interactive shells, the other for non-interactive shells (e.g. plugin-host)
    # Clean up
    apt-get autoremove -y; \
    apt-get clean -y; \
    rm -rf /var/lib/apt/lists/*
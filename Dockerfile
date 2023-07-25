#################################################################################
# Copyright (c) 2022,2023 T-Systems International GmbH
# Copyright (c) 2022,2023 Contributors to the Eclipse Foundation
#
# See the NOTICE file(s) distributed with this work for additional
# information regarding copyright ownership.
#
# This program and the accompanying materials are made available under the
# terms of the Apache License, Version 2.0 which is available at
# https://www.apache.org/licenses/LICENSE-2.0.
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
# WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
# License for the specific language governing permissions and limitations
# under the License.
#
# SPDX-License-Identifier: Apache-2.0
################################################################################
 
# our base build image
FROM maven:3.8.7-eclipse-temurin-17 AS builder

# copy the project files
COPY ./pom.xml /pom.xml

# build all dependencies
RUN mvn dependency:go-offline -B 

# copy your other files
COPY ./src ./src

# build for release
RUN mvn clean install -Dmaven.test.skip=true 


FROM eclipse-temurin:17.0.6_10-jdk

ARG USERNAME=user
ARG USER_UID=1000
ARG USER_GID=$USER_UID

# Create the user
RUN groupadd --gid $USER_GID $USERNAME \
    && useradd --uid $USER_UID --gid $USER_GID -m $USERNAME 

USER $USERNAME

WORKDIR /proxyservice

# copy over the built artifact from the maven image
COPY --from=builder target/*.jar ./app.jar

EXPOSE 10092
# set the startup command to run your binary
CMD ["java", "-jar", "./app.jar"]
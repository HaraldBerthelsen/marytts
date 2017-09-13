FROM openjdk

RUN apt-get update -y && apt-get upgrade -y && apt-get install apt-utils -y
RUN apt-get install wget


## INSTALL MARYTTS
RUN git clone https://github.com/HaraldBerthelsen/marytts.git
RUN cd marytts && ./gradlew build && ./gradlew installDist


## INSTALL ADDON VOICES
RUN cp marytts/stts_voices/* marytts/build/install/marytts/lib/

## NORWEGIAN
RUN wget https://github.com/HaraldBerthelsen/voice-stts_no_nst-hsmm/releases/download/v5.2/voice-stts_no_nst-hsmm-5.2.zip && unzip voice-stts_no_nst-hsmm-5.2.zip && mv lib/voice-stts_no_nst-hsmm-5.2.jar /marytts/build/install/marytts/lib/ && rm voice-stts_no_nst-hsmm-5.2.zip

## ARABIC
RUN cd marytts/build/install/marytts/lib/ && wget https://github.com/HaraldBerthelsen/voice-ar-nah-hsmm/releases/download/v5.2/voice-ar-nah-hsmm-5.2.jar


RUN echo "echo 'AVAILABLE VOICES:' && ls marytts/build/install/marytts/lib/ | egrep ^voice | sed 's/.jar//' | sed 's/^/* /' " > /bin/voices
RUN chmod +x /bin/voices

## LIST VOICES
RUN voices


## CMD SETTINGS
EXPOSE 59125

CMD (cd marytts && ./gradlew run)

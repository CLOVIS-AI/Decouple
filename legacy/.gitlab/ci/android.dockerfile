FROM openjdk:17-bullseye AS builder

ENV ANDROID_COMPILE_SDK="33"
ENV ANDROID_BUILD_TOOLS="33.0.0"
ENV ANDROID_SDK_TOOLS="8512546_latest"

RUN wget --quiet --output-document=android-sdk.zip https://dl.google.com/android/repository/commandlinetools-linux-$ANDROID_SDK_TOOLS.zip
RUN unzip -d /opt/android android-sdk.zip
RUN rm android-sdk.zip

# The cmdline-tools are in SDK/cmdline-tools in the archive, but should be in SDK/cmdline-tools/tools
# https://stackoverflow.com/a/61176718
WORKDIR /opt/android/cmdline-tools
RUN mkdir -p tools
RUN ls
RUN mv bin lib source.properties NOTICE.txt tools
ENV ANDROID_SDK_ROOT=/opt/android
ENV PATH="$PATH:/opt/android/cmdline-tools/tools/bin:/opt/android/platform-tools"
WORKDIR /root

RUN yes | sdkmanager "platforms;android-$ANDROID_COMPILE_SDK" >/dev/null
RUN yes | sdkmanager "platform-tools" >/dev/null
RUN yes | sdkmanager "build-tools;$ANDROID_BUILD_TOOLS" >/dev/null
RUN yes | sdkmanager --licenses >/dev/null

FROM openjdk:17-bullseye

ENV ANDROID_SDK_ROOT=/opt/android
ENV PATH="$PATH:/opt/android/cmdline-tools/tools/bin:/opt/android/platform-tools"
COPY --from=builder /opt/android /opt/android


# auto reload changes is configured in application.conf with
# development = true
# and
# watch = [ classes ]

# this build with watch changes on CMD+S needs to be running for the auto-reload to work
./gradlew -t build


# there may be some issues with auto reload. run http request and check the server logs. some cases can start producing error logs, if response models are modified. doesn't properly reload everything?
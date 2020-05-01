# Clean
rm -rf app/build

# Zip
zip -r -0 X-Logcat-lib.zip *

# Upload
OUTPUT=$(curl -F "file=@X-Logcat-lib.zip" https://file.io)

# Extract url from output
URL=$(echo $OUTPUT | grep -oP "https://file.io/........")

# Copy url to clipboard
termux-clipboard-set "wget -O X-Logcat-Lib.zip $URL"

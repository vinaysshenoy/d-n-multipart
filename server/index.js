const express = require('express');
const fs = require('fs');
const app = express();

app.get('/', (req, res) => {

  const boundary = Date.now().toString();
  res.writeHead(200, {
    'Content-Type': `multipart/mixed; boundary=${boundary}`,
    'Connection': 'keep-alive'
  });
  res.write('\r\n');

  const data = JSON.stringify({
    val1: 5,
    val2: "10",
    val3: ["a", "b", "c", "d", "e"]
  });
  res.write(`\r\n--${boundary}\n`);
  res.write(`Content-Disposition: attachment; name="data1"\n`);
  res.write(`Content-Type: application/json; charset=utf-8\n`);
  res.write(`Content-Length: ${Buffer.byteLength(data, 'utf8')}\n\n`);
  res.write(data, 'utf8');

  const data2 = JSON.stringify({
    val1: "abcd"
  });
  res.write(`\r\n--${boundary}\n`)
  res.write(`Content-Disposition: attachment; name="data2"\n`);
  res.write(`Content-Type: application/json; charset=utf-8\n`);
  res.write(`Content-Length: ${Buffer.byteLength(data2, 'utf8')}\n\n`);
  res.write(data2, 'utf8');

  const file = fs.readFileSync('image.jpg');
  res.write(`\r\n--${boundary}\n`)
  res.write(`Content-Disposition: attachment; name="image"; filename="image.jpg"\n`);
  res.write(`Content-Type: image/jpeg\n`);
  res.write(`Content-Length: ${file.byteLength}\n\n`);
  res.write(file);

  res.write(`\r\n--${boundary}--`);
  res.end('ok');

});

app.listen(3000);
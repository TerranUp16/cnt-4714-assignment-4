docker-compose up -d;

Write-Host "`nImporting database content:";

[bool] $flag=$true;

while ($flag) {
  try {
    docker exec -t mysql8 bash -c 'echo -e "[client]\\nuser=root\\npassword=M7vDRWBJW1EAGzih" > /root/.my.cnf; mysql < /root/project4dbscript.sql' *>$null;
  } catch {

  }

  if ($LASTEXITCODE -eq 0) {
    $flag=$false;
    Write-Host "`nDatabase import complete! All services available." -ForegroundColor Green;
  } else {
    $flag=$true;
    Write-Host "." -NoNewline ;
    Start-Sleep -Seconds 1;
  }
}
import shutil
import requests

i=0 
j=0
k=0
a='A'
b='A'
#   text=a+b+'000'+'000'

while(i<2):
    i=i+1
    while(j<2):
        j=j+1
        while(k<5):
          k=k+1;
          ni=str(k)
          ui=str(j)
          text=a+b+ui.zfill(3)+ni.zfill(3)
          url = 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data='+text
          response = requests.get(url, stream=True)
          with open(text+'.png', 'wb') as out_file:
                 shutil.copyfileobj(response.raw, out_file)
          del response
        k=0
    b=chr(ord(b)+1)
    j=0


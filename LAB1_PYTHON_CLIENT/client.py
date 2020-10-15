import requests

letters = input("Give letters:")
dict_post = {"letters":letters}
url = "http://localhost:8080/tema1-1.0-SNAPSHOT/tema"
r = requests.post(url, data = dict_post)
print(r.text)
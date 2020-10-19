import requests
import concurrent.futures
from random import choice
from string import ascii_uppercase
import time
import json

url = r"http://localhost:8080/tema1-1.0-SNAPSHOT/tema"
file_format = r"strest_test_report_{}.json"
method = "POST"
number_of_letters = 4
number_of_threads = 500

return_dict = {"execution_time": None,
               "maxim_request_time": None,
               "medium_request_time": None,
               "method": None,
               "nr_of_letters": None,
               "nr_of_threads": None
               }


def all_request(params):

    data = {"letters": params[0]}

    if params[1] == "GET":
        r = requests.get(url, params=data)
    else:
        if params[1] == "POST":
            r = requests.post(url, data=data)

    if r.status_code != 200:
        print("bad request response")
        print(r.status_code)


initial_start_time = time.time()
medium_time = 0
maxim_time = 0
suma = 0
with concurrent.futures.ThreadPoolExecutor(max_workers=number_of_threads) as executor:
    for i in range(number_of_threads):
        start_time = time.time()
        letters = ''.join(choice(ascii_uppercase) for j in range(number_of_letters)).lower()

        executor.submit(all_request, (letters, method))
        finish_time = time.time()
        print("thread {} has finished in {}".format(i, finish_time-start_time))
        # time.sleep(3)
        if finish_time - start_time > maxim_time:
            maxim_time = finish_time - start_time
            maxim_value = i
        suma += (finish_time - start_time)

medium_time = suma/128
last_finish_time = time.time()
print("all thread time: {}".format(last_finish_time-initial_start_time))

return_dict['medium_request_time'] = medium_time
return_dict['execution_time'] = last_finish_time-initial_start_time
return_dict['maxim_request_time'] = maxim_time
return_dict['method'] = method
return_dict['nr_of_letters'] = number_of_letters
return_dict['nr_of_threads'] = number_of_threads

with open(file_format.format(time.time()), "w") as f:
    json.dump(return_dict, f, indent=4)


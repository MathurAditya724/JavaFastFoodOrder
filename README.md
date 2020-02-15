# Java Fast Food Order

In this, I have used Java for creating the interface for the chef and client. And for fetching the data from a server, I created a server with **Python** and **Django**, and used the **DjangoRestFramework** for creating the APIs. For communicating with the server I have utilized the class of the **java.net** package.

## Getting Started

For starting the project, First, create a virtual environment for the python Lib's in the *requirement.txt* file.

``` cmd
python -m venv ./venv
```

*Activate* the virtual environment and download the required Lib's.

``` cmd
pip install -r requirement.txt
```

Before going any further, just create an SQLite file in the *fastfood* folder and then *cd* into the folder and simply migrate and create the superuser in the database using Django.
And after doing this setup, run the server.

``` cmd
cd ./fastfood
python manage.py migrate
python manage.py createsuperuser
python manage.py runserver
```

## Basic Setup

The APIs work out of the box, What you need to do is upload images and data onto the server. I have included the *Images* folder which includes all the images I have used. Just go to <http://localhost:8000/admin> and Log in with your username and password that you have created in the createsuperuser part. Under the *Items* heading you will find all the headers which will require the images (except the *Order* header).

When uploaded all the images, compile and run the *client.java* file. And that's it, choose an item and a drink, and click on validate order for sending a POST request to the server and placing an order. Now if you compile and run the *chef.java* file, you will see all the orders placed.

## Where are the APIs

If you want to see the APIs, go to <http://localhost:8000/api/item> here you can see all the items. If you want to see all the routes of the API, just open the *urls.py* file, which is in *fastfood/item/urls.py*.

## Build With

- Java (JDK 11) - For the GUI Interface of the chef and the client
- org.json Package - For handling the JSON data from the server
- Python - For working with Django
- Django - For creating the server
- Django RestFramework - For the APIs

## Authors

**Aditya Mathur**, you can find me on [Twitter](https://twitter.com/mathuraditya7) or [LinkedIn](https://www.linkedin.com/in/aditya-mathur-7240/)

## License

This project is licensed under the MIT License

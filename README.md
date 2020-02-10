# Java Fast Food Order

In this I have used Java for creating the interface for the chef and client. And for fetching the data from a server, I created a server with **Python** and **Django**, and used the **DjangoRestFramework** for creating the API's. For comunicating with the server I have utilized the class of the **java.net** package.

## Getting Started
For starting the project, Fisrt create a virtual enviroment for the python Lib's in the *requirement.txt* file.

``` cmd
python -m venv ./venv
```

*Activate* the virtual enviroment and download the required Lib's.

``` cmd
pip install -r requirement.txt
```

Before going any futher, just create a sqlite file in the *fastfood* folder and then *cd* into the folder and simply migrate and create the super user in the database using Django.
And after doing this setup, run the server.

``` cmd
cd ./fastfood
python manage.py migrate
python manage.py createsuperuser
python manage.py runserver
```

## Basic Setup
The API's work out of the box, What you need to do is upload images and data onto the server. I have included the *Images* folder which include all the images I have used. Just goto http://localhost:8000/admin and Log in with your username and password that you have created in the createsuperuser part. Under the *Items* heading you will find all the headers which will require the images (except the *Order* header).

When uploaded all the images, compile and run the *main.java* file.

## Build With
- Java (JDK 11) - For the GUI Interface of the chef and the client
- org.json Package - For handling the JSON data from the server
- Python - For working with Django
- Django - For creating the server
- Django RestFramework - For the API's

## Authors
**Aditya Mathur**, you can find me on [Twitter](https://twitter.com/mathuraditya7) or [LinkedIn](https://www.linkedin.com/in/aditya-mathur-7240/)

## License
This project is licensed under the MIT License
# Java Fast Food Order

In this I have used Java for creating the interface for the chef and client. And for fetching the data from a server, I created a server with **Python** and **Django**, and used the **DjangoRestFramework** for creating the API's. For comunicating with the server I have utilized the class of the **java.net** package.

For starting the project, Fisrt create a virtual enviroment for the python Lib's in the *requirement.txt* file.
```
python -m venv ./venv
```
*Activate* the virtual enviroment and download the required Lib's.
```
pip install -r requirement.txt
```
Before going any futher, just create a sqlite file in the *fastfood* folder and then *cd* into the folder and simply migrate and create the super user in the database using Django.
```
cd ./fastfood
python manage.py migrate
python manage.py createsuperuser
python manage.py runserver
```

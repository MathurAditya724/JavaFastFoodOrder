from django.db import models

class Banner(models.Model):
    image = models.ImageField(default="default.jpg", upload_to="banner", blank=True)

class Item(models.Model):
    name = models.CharField(max_length=255)
    image = models.ImageField(default="default.jpg", upload_to="items", blank=True)
    price = models.SmallIntegerField()

class Drink(models.Model):
    name = models.CharField(max_length=255)
    image = models.ImageField(default="default.jpg", upload_to="items", blank=True)
    price = models.SmallIntegerField()

class Order(models.Model):
    quantity = models.SmallIntegerField()
    item = models.ForeignKey(Item, on_delete=models.CASCADE)
    drink = models.ForeignKey(Drink, on_delete=models.CASCADE)
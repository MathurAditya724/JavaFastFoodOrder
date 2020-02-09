from rest_framework import generics
from .serializer import *

class BannerRetrieveListView(generics.ListAPIView):
    serializer_class = BannerSerializer
    queryset = Banner.objects.all()

class ItemRetrieveListView(generics.ListAPIView):
    serializer_class = ItemSerializer
    queryset = Item.objects.all()

class DrinkRetrieveListView(generics.ListAPIView):
    serializer_class = DrinkSerializer
    queryset = Drink.objects.all()

class OrderRetrieveListView(generics.ListAPIView):
    serializer_class = OrderSerializer
    queryset = Order.objects.all()

class OrderCreateView(generics.CreateAPIView):
    serializer_class = OrderCreateSerializer
    queryset = Order.objects.all()
from django.urls import path
from django.conf import settings
from django.conf.urls.static import static
from .views import *

urlpatterns = [
    path('banner/', BannerRetrieveListView.as_view()),
    path('item/', ItemRetrieveListView.as_view()),
    path('drink/', DrinkRetrieveListView.as_view()),
    path('order/', OrderRetrieveListView.as_view()),
    path('order/create/', OrderCreateView.as_view()),
]

if settings.DEBUG:
    urlpatterns += static(settings.MEDIA_URL,
                          document_root=settings.MEDIA_ROOT)
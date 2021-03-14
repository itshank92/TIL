## template에서 사용할 필터만들기









```python


# ---------------------------------- [edit] ---------------------------------- #
from django import template

register = template.Library()


@register.filter
def sub(value, arg):
    return value - arg
# ---------------------------------------------------------------------------- #
```



```python
from django.template.defaulttags import register
...
@register.filter
def get_item(dictionary, key):
    return dictionary.get(key)
```


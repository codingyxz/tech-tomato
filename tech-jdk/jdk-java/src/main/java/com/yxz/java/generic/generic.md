1、getGenericSuperClass()

getGenericSuperclass() 方法是Java中的Class类的一个方法，用于获取表示一个类的泛型父类的类型信息， 包括泛型参数信息。
它返回一个Type对象，通常是一个ParameterizedType，但也可能是其他类型。

以下是有关getGenericSuperclass()方法的详细解释：

作用：
getGenericSuperclass()方法的主要作用是在运行时获取当前类的泛型父类的类型信息。
这在反射和泛型编程中非常有用，因为它允许您动态地了解和操作类的泛型参数信息。
返回值类型：
getGenericSuperclass()方法的返回类型是Type，这是一个Java中的通用接口，表示所有类型的超级接口。
通常情况下，返回的Type对象是一个ParameterizedType，但在某些情况下也可能是其他类型，例如Class。

2、ParameterizedType

ParameterizedType 是 Java 中的一个接口，用于表示参数化类型，即具有泛型参数的类型。它提供了一种机制来在运行时获取和操作泛型类型的信息。下面是对 ParameterizedType 的详细解释：

什么是 ParameterizedType？
ParameterizedType 是 Java 泛型系统的一部分，用于表示类或接口的泛型类型信息。它用于捕获包含泛型参数的类的类型，例如 List<String> 或 Map<Integer, String>。

ParameterizedType 接口的方法：
ParameterizedType 接口包含以下几个主要方法来访问泛型类型的信息：

Type[] getActualTypeArguments(): 返回一个 Type 数组，其中包含了泛型参数的实际类型。例如，List<String> 的实际类型参数是 String，所以该方法将返回包含 String 的 Type 数组。
Type getRawType(): 返回原始类型，即没有泛型参数的类型。例如，List<String> 的原始类型是 List。
Type getOwnerType(): 如果该类型是一个成员类型，例如嵌套类，那么返回该成员类型的拥有者类型。通常情况下，这是一个包含该成员类型的外部类。
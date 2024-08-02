# Columns Library

An Android library that provides a custom view to display a list of columns.

## Installation

## Usage

To use the Columns view in your project, add the following XML code to your layout file:

```xml
<com.example.graphs.DiagramView
    android:id="@+id/diagramView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
/>
```

Then, in your activity or fragment, you can use the DiagramView class to display a list of columns:

```java
DiagramView diagramView = findViewById(R.id.diagramView);
diagramView.setColumns(columns);
```

columns is a list of Column objects that you can create using the Column class:

```java
List<Column> columns = new ArrayList<>();
columns.add(new Column("Column 1", 100));
columns.add(new Column("Column 2", 200));
columns.add(new Column("Column 3", 300));
```

## Customization

```java
diagramView.setAnimateColumns(true);
diagramView.setTextBold(true);
diagramView.setTheBackgroundColor(Color.parseColor("#FFFFFF"));
diagramView.setColumnsColor(Color.parseColor("#FF5722"));
diagramView.setColumnsTextColor(Color.parseColor("#000000"));
diagramView.setColumnsTextSize(40);
diagramView.setSpaceBetweenColumns(10);
```

## Contributing
Daniel Ben-Avi

## Example
https://github.com/user-attachments/assets/2c76d6b3-03a9-4744-8ff6-2d83669931a5


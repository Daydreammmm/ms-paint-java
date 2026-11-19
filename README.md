# ms-paint-java

A paint app in JavaFX. I made it for CSC207 at UTM, with a group at first, then kept working on it after.

You can draw circles, rectangles, squares, ovals, triangles, polylines, squiggles, and text, plus an eraser. There's a colour picker, opacity, stroke width, fill/outline (M), undo (Ctrl+Z), redo (Ctrl+Y), delete, clear canvas, dark mode, and you can open multiple canvases as tabs and rename them.

I also added a colourblind-safe palette, since the default colours weren't great for that.

How it's put together:
- PaintModel holds all the shapes and notifies the panel when something changes
- Each shape has its own strategy class (CircleStrat, RectangleStrat, etc.) — PaintPanel just calls the same mouse events and the strategy handles what to draw
- Undo/redo keeps a stack of changes (add/delete, which shape, where it was) so it can reverse them

How to run:
cd Assignment2
mvn clean javafx:run
Needs Java 22.

What's not done yet:
- Save/Open in the menu don't actually do anything yet, that's the next thing I want to add

# MS Paint Clone

A drawing application in JavaFX, originally built for a CSC207 project at the University of Toronto Mississauga.
Started as a group project, and later refined independently to add in more custom features.

## Features
- Drawing tools: Circle, Rectangle, Square, Oval, Triangle, Polyline, Squiggle, Text, Eraser
- Colour picker, opacity control (0–100%), stroke width, fill / outline mode (M)
- Undo (Ctrl+Z) and Redo (Ctrl+Y), Delete, Clear Canvas
- Dark Mode, colour-vision-deficiency-safe palette
- Multiple canvases as tabs, with rename
- Keyboard shortcuts: Ctrl+Z undo, Ctrl+Y redo, Delete, +/- stroke width, M fill/outline

## Architecture
- MVC + Observer: `PaintModel extends Observable`, `PaintPanel` observes the model and redraws on change
- Strategy: `DrawingStrat` interface with a strategy per shape (`CircleStrat`, `RectangleStrat`, etc.). `Context.getDrawingStrat()` acts as a factory, returning the strategy for the current mode
- Command: undo/redo stores each add/delete as a `Change` object (type, shape, index) on undo/redo deques in `PaintModel`

## How to run
Requires Java 22 and Maven.

```bash
cd Assignment2
mvn clean javafx:run

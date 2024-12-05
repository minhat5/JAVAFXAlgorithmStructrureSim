package application;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Controller {
	@FXML
	private Button searchButton;
	@FXML
	private Button sortButton;
	@FXML
	private Pane searchPane;
	@FXML
	private Pane sortPane;
	@FXML
	private Button linearSearchButton;
	@FXML
	private Button binarySearchButton;
	@FXML
	private Pane inputPane;
	@FXML
	private TextField inputTextField;
	@FXML
	private Label interfaceLabel;
	@FXML
	private Label notificationLabel;
	@FXML
	private Label notificationOperationLabel;
	@FXML
	private HBox squareHBox;
	@FXML
	private Pane squareOperationPane;
	@FXML
	private Pane linePane;
	@FXML
	private HBox sortSquareHBox;
	@FXML
	private Button interchangeSortButton;
	@FXML
	private Button selectionSortButton;
	@FXML
	private Button bubbleSortButton;
	@FXML
	private Button insertionSortButton;
	@FXML
	private Button binaryInsertionSortButton;
	@FXML
	private Label sortNotificationOperationLabel;
	@FXML
	private Line sortLine;
	@FXML
	private Button shakerSortButton;
	@FXML
	private Button shellSortButton;
	
	private int numberOfElements;
	private List<Integer> arrayElements = new ArrayList<>();
	private int findNumber;
	private int currentIndex = 0;
	private int left;
	private int right;
	private int mid;
	private boolean found = false;
	private List<Rectangle> squares = new ArrayList<>();

	public void convertToDefault() {
		squareHBox.getChildren().clear();
		squareOperationPane.getChildren().clear();
		sortSquareHBox.getChildren().clear();
		notificationLabel.setText(null);
		currentIndex = 0;
		arrayElements.clear();
		notificationOperationLabel.setText(null);
		found = false;
		linePane.getChildren().clear();
		left = 0;
		right = 0;
		squares.clear();
		sortNotificationOperationLabel.setText(null);
	}

	public void handleClicks(ActionEvent eventButton) {
		if (eventButton.getSource() == searchButton) {
			searchPane.toFront();
		}

		if (eventButton.getSource() == sortButton) {
			sortPane.toFront();
		}

		if (eventButton.getSource() == linearSearchButton) {
			convertToDefault();
			inputPane.toFront();
			interfaceLabel.setText("Enter number of elements in array: ");
			inputTextField.setOnKeyPressed(event -> {
				if (event.getCode() == KeyCode.ENTER) {
					inputNumberOfElements(inputTextField, () -> {
						inputElementsInArray(() -> {
							interfaceLabel.setText("Enter the number you want to find: ");
							inputFindNumber(() -> {
								addSquares(squareHBox, arrayElements);
								linearSearchAlgorithm(arrayElements, findNumber, squareHBox, squareOperationPane);
							});
						});
					});
				}
			});
		}

		if (eventButton.getSource() == binarySearchButton) {
			convertToDefault();
			inputPane.toFront();
			interfaceLabel.setText("Enter number of elements in array: ");
			inputTextField.setOnKeyPressed(event -> {
				if (event.getCode() == KeyCode.ENTER) {
					inputNumberOfElements(inputTextField, () -> {
						inputElementsInArray(() -> {
							if (isIncreasingArray(arrayElements)) {
								interfaceLabel.setText("Enter the number you want to find: ");
								inputFindNumber(() -> {
									addSquares(squareHBox, arrayElements);
									binarySearchAlgorithm(arrayElements, findNumber, squareHBox, squareOperationPane,
											linePane);
								});
							} else {
								interfaceLabel.setText("Press button (Binary Search) again");
								notificationLabel.setText("This array must be an increasing array!");
								inputTextField.clear();
							}
						});
					});
				}
			});
		}

		if (eventButton.getSource() == interchangeSortButton) {
			convertToDefault();
			inputPane.toFront();
			interfaceLabel.setText("Enter number of elements in array: ");
			inputTextField.setOnKeyPressed(event -> {
				if (event.getCode() == KeyCode.ENTER) {
					inputNumberOfElements(inputTextField, () -> {
						inputElementsInArray(() -> {
							addSquares(sortSquareHBox, arrayElements);
							new Thread(() -> {
								interchangeSort();
								Platform.runLater(() -> {
									sortNotificationOperationLabel.setText(
											"Sort complete!\nIf you want to do this algorithm, press button (Interchange Sort) again!");
								});
							}).start();
						});
					});
				}
			});
		}

		if (eventButton.getSource() == selectionSortButton) {
			convertToDefault();
			inputPane.toFront();
			interfaceLabel.setText("Enter number of elements in array: ");
			inputTextField.setOnKeyPressed(event -> {
				if (event.getCode() == KeyCode.ENTER) {
					inputNumberOfElements(inputTextField, () -> {
						inputElementsInArray(() -> {
							addSquares(sortSquareHBox, arrayElements);
							new Thread(() -> {
								selectionSort();
								Platform.runLater(() -> {
									sortNotificationOperationLabel.setText(
											"Sort complete!\nIf you want to do this algorithm, press button (Selection Sort) again!");
								});
							}).start();
						});
					});
				}
			});
		}
		if (eventButton.getSource() == bubbleSortButton) {
			convertToDefault();
			inputPane.toFront();
			interfaceLabel.setText("Enter number of elements in array: ");
			inputTextField.setOnKeyPressed(event -> {
				if (event.getCode() == KeyCode.ENTER) {
					inputNumberOfElements(inputTextField, () -> {
						inputElementsInArray(() -> {
							addSquares(sortSquareHBox, arrayElements);
							new Thread(() -> {
								bubbleSort();
								Platform.runLater(() -> {
									sortNotificationOperationLabel.setText(
											"Sort complete!\nIf you want to do this algorithm, press button (Bubble Sort) again!");
								});
							}).start();
						});
					});
				}
			});
		}
		if (eventButton.getSource() == insertionSortButton) {
			convertToDefault();
			inputPane.toFront();
			interfaceLabel.setText("Enter number of elements in array: ");
			inputTextField.setOnKeyPressed(event -> {
				if (event.getCode() == KeyCode.ENTER) {
					inputNumberOfElements(inputTextField, () -> {
						inputElementsInArray(() -> {
							addSquares(sortSquareHBox, arrayElements);
							new Thread(() -> {
								insertionSort();
								Platform.runLater(() -> {
									sortNotificationOperationLabel.setText(
											"Sort complete!\nIf you want to do this algorithm, press button (Insertion Sort) again!");
								});
							}).start();
						});
					});
				}
			});
		}
		if (eventButton.getSource() == binaryInsertionSortButton) {
			convertToDefault();
			inputPane.toFront();
			interfaceLabel.setText("Enter number of elements in array: ");
			inputTextField.setOnKeyPressed(event -> {
				if (event.getCode() == KeyCode.ENTER) {
					inputNumberOfElements(inputTextField, () -> {
						inputElementsInArray(() -> {
							addSquares(sortSquareHBox, arrayElements);
							new Thread(() -> {
								binaryInsertionSort();
								Platform.runLater(() -> {
									sortNotificationOperationLabel.setText(
											"Sort complete!\nIf you want to do this algorithm, press button (Binary Insertion Sort) again!");
								});
							}).start();
						});
					});
				}
			});
		}
		if (eventButton.getSource() == shakerSortButton) {
			convertToDefault();
			inputPane.toFront();
			interfaceLabel.setText("Enter number of elements in array: ");
			inputTextField.setOnKeyPressed(event -> {
				if (event.getCode() == KeyCode.ENTER) {
					inputNumberOfElements(inputTextField, () -> {
						inputElementsInArray(() -> {
							addSquares(sortSquareHBox, arrayElements);
							new Thread(() -> {
								shakerSort();
								Platform.runLater(() -> {
									sortNotificationOperationLabel.setText(
											"Sort complete!\nIf you want to do this algorithm, press button (Binary Insertion Sort) again!");
								});
							}).start();
						});
					});
				}
			});
		}
		if (eventButton.getSource() == shellSortButton) {
			convertToDefault();
			inputPane.toFront();
			interfaceLabel.setText("Enter number of elements in array: ");
			inputTextField.setOnKeyPressed(event -> {
				if (event.getCode() == KeyCode.ENTER) {
					inputNumberOfElements(inputTextField, () -> {
						inputElementsInArray(() -> {
							addSquares(sortSquareHBox, arrayElements);
							new Thread(() -> {
								shellSort();
								Platform.runLater(() -> {
									sortNotificationOperationLabel.setText(
											"Sort complete!\nIf you want to do this algorithm, press button (Binary Insertion Sort) again!");
								});
							}).start();
						});
					});
				}
			});
		}
	}

	public void inputNumberOfElements(TextField inputTextField, Runnable nextStepAction) {
		try {
			numberOfElements = Integer.parseInt(inputTextField.getText().trim());
			if (numberOfElements <= 0 || numberOfElements > 20) {
				notificationLabel.setText("Number of elements in array must be\na positive number and equal or lower\nthan 20!");
				inputTextField.clear();
			} else {
				notificationLabel.setText("Number of elements in array is: " + numberOfElements);
				inputTextField.clear();
				interfaceLabel.setText("Enter each element in array: ");
				inputTextField.setOnKeyPressed(event -> {
					if (event.getCode() == KeyCode.ENTER) {
						nextStepAction.run();
					}
				});
			}
		} catch (NumberFormatException e) {
			notificationLabel.setText("Number of elements in array must be a positive number!");
			inputTextField.clear();
		}
	}

	public void inputElementsInArray(Runnable nextStepAction) {
		if (arrayElements.size() < numberOfElements) {
			try {
				int element = Integer.parseInt(inputTextField.getText().trim());
				notificationLabel.setText("Element: " + element);
				inputTextField.clear();
				arrayElements.add(element);
			} catch (NumberFormatException e) {
				notificationLabel.setText("Enter only integer!");
				inputTextField.clear();
			}
		} else {
			notificationLabel.setText("You have entered enough elements!");
			inputTextField.clear();
			inputTextField.setOnKeyPressed(event -> {
				if (event.getCode() == KeyCode.ENTER) {
					nextStepAction.run();
				}
			});
		}
	}

	public void inputFindNumber(Runnable nextStepAction) {
		try {
			findNumber = Integer.parseInt(inputTextField.getText().trim());
			notificationLabel.setText("The number you want to find is: " + findNumber);
			inputTextField.clear();
			nextStepAction.run();
		} catch (NumberFormatException e) {
			notificationLabel.setText("Enter only integer!");
			inputTextField.clear();
		}
	}

	public void addSquares(HBox squareHBox, List<Integer> arrayElements) {
		squareHBox.setSpacing(3);
		double totalSpacing = (arrayElements.size() - 1) * squareHBox.getSpacing();
		double squareSize = (squareHBox.getWidth() - totalSpacing) / arrayElements.size();

		for (int i = 0; i < arrayElements.size(); i++) {
			Rectangle square = new Rectangle(squareSize, squareSize);
			square.setFill(Color.LIGHTBLUE);
			square.setStroke(Color.BLACK);
			squares.add(square);

			Text text = new Text(String.valueOf(arrayElements.get(i)));
			text.setFill(Color.BLACK);

			StackPane stackPane = new StackPane();
			stackPane.getChildren().addAll(squares.get(i), text);
			squareHBox.getChildren().addAll(stackPane);
		}
	}

	public void linearSearchAlgorithm(List<Integer> array, int findNumber, HBox squareHBox, Pane squareOperationPane) {
		double totalSpacing = (arrayElements.size() - 1) * squareHBox.getSpacing();
		double squareSize = (squareHBox.getWidth() - totalSpacing) / arrayElements.size();
		Rectangle findSquare = new Rectangle(squareSize, squareSize);
		findSquare.setFill(Color.RED);
		findSquare.setStroke(Color.BLACK);
		Text text = new Text(String.valueOf(findNumber));
		StackPane stackPane = new StackPane();
		stackPane.getChildren().addAll(findSquare, text);
		squareOperationPane.getChildren().add(stackPane);
		TranslateTransition translateSquare = new TranslateTransition(Duration.seconds(1), findSquare);
		TranslateTransition translateText = new TranslateTransition(Duration.seconds(1), text);

		translateSquare.setOnFinished(event -> {
			if (currentIndex < array.size() - 1 && array.get(currentIndex) != findNumber) {
				translateSquare.setByX(squareSize + squareHBox.getSpacing() + 2);
				translateSquare.playFromStart();
				translateText.setByX(squareSize + squareHBox.getSpacing() + 2);
				translateText.playFromStart();
				currentIndex++;
			} else if (currentIndex < array.size() && array.get(currentIndex) == findNumber) {
				StackPane takeStackPane = (StackPane) squareHBox.getChildren().get(currentIndex);
				Rectangle square = (Rectangle) takeStackPane.getChildren().get(0);
				square.setFill(Color.RED);
				notificationOperationLabel.setText(
						"Search complete!\nIf you want to do this algorithm, press button (Linear Search) again!");
			} else if (currentIndex == array.size() - 1 && array.get(currentIndex) != findNumber) {
				notificationOperationLabel.setText("Don't have any element which is " + findNumber
						+ " in array\nIf you want to do this algorithm, press button (Linear Search) again!");
			}
		});
		translateSquare.play();
		translateText.play();
	}

	public Boolean isIncreasingArray(List<Integer> arrayElements) {
		for (int i = 1; i < arrayElements.size(); i++) {
			if (arrayElements.get(i - 1) > arrayElements.get(i)) {
				return false;
			}
		}
		return true;
	}

	public void binarySearchAlgorithm(List<Integer> array, int findNumber, HBox squareHBox, Pane squareOperationPane,
			Pane linePane) {
		left = 0;
		right = array.size() - 1;
		mid = (left + right) / 2;

		Line line = new Line();
		line.setStroke(Color.TEAL);
		line.setStrokeWidth(5);
		linePane.getChildren().add(line);

		double totalSpacing = (arrayElements.size() - 1) * squareHBox.getSpacing();
		double squareSize = (squareHBox.getWidth() - totalSpacing) / arrayElements.size();
		double midPositionX = mid * (squareSize + squareHBox.getSpacing() + 2);

		Rectangle findSquare = new Rectangle(squareSize, squareSize);
		findSquare.setFill(Color.RED);
		findSquare.setStroke(Color.BLACK);

		Text text = new Text(String.valueOf(findNumber));
		StackPane stackPane = new StackPane();
		stackPane.getChildren().addAll(findSquare, text);
		stackPane.setTranslateX(midPositionX);

		line.setStartY(0);
		line.setEndY(0);
		double leftPosition = left * (squareSize + squareHBox.getSpacing() + 2) + squareSize / 2;
		double rightPosition = right * (squareSize + squareHBox.getSpacing() + 2) + squareSize / 2;
		line.setStartX(leftPosition);
		line.setEndX(rightPosition);

		squareOperationPane.getChildren().add(stackPane);

		TranslateTransition translateSquare = new TranslateTransition(Duration.seconds(1), findSquare);
		TranslateTransition translateText = new TranslateTransition(Duration.seconds(1), text);
		TranslateTransition translateLine = new TranslateTransition(Duration.seconds(1), line);

		PauseTransition pause = new PauseTransition(Duration.seconds(1));

		pause.setOnFinished(event -> {
			if (left <= right && !found) {
				mid = (left + right) / 2;
				double newMidPositionX = mid * (squareSize + squareHBox.getSpacing() + 2);
				double currentPositionX = stackPane.getTranslateX();
				double moveBy = newMidPositionX - currentPositionX;
				translateSquare.setToX(moveBy);
				translateText.setToX(moveBy);

				translateSquare.setOnFinished(e -> {
					if (array.get(mid) == findNumber) {
						findSquare.setFill(Color.GREEN);
						found = true;
						notificationOperationLabel.setText(
								"Search complete!\nIf you want to do this algorithm, press button (Binary Search) again!");
						return;
					}
				});
				double leftPositionX;
				double rightPositionX;
				if (left < right) {
					leftPositionX = left * (squareSize + squareHBox.getSpacing() + 2) + squareSize / 2;
					rightPositionX = right * (squareSize + squareHBox.getSpacing() + 2) + squareSize / 2;
				} else {
					leftPositionX = mid * (squareSize + squareHBox.getSpacing() + 2) + squareSize / 2;
					rightPositionX = mid * (squareSize + squareHBox.getSpacing() + 2) + squareSize / 2;
					line.setStartX(leftPositionX);
					line.setEndX(rightPositionX);
				}
				double distance = rightPositionX - leftPositionX;
				double duration = distance / 1000;

				double newEndX = rightPositionX;

				Timeline timeline = new Timeline();
				KeyFrame keyFrame = new KeyFrame(Duration.seconds(duration), new KeyValue(line.endXProperty(), newEndX),
						new KeyValue(line.startXProperty(), leftPositionX));
				timeline.getKeyFrames().add(keyFrame);
				timeline.play();

				translateSquare.play();
				translateText.play();
				translateLine.play();

				if (array.get(mid) < findNumber) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
				pause.playFromStart();
			} else if (!found) {
				findSquare.setFill(Color.GRAY);
				notificationOperationLabel.setText("Don't have any element which is " + findNumber
						+ " in array\nIf you want to do this algorithm, press button (Binary Search) again!");
			}
		});
		pause.play();
	}

	public void interchangeSort() {
		int size = arrayElements.size();
		for (int i = 0; i < size - 1; i++) {
			squares.get(i).setFill(Color.RED);
			for (int j = i + 1; j < size; j++) {
				squares.get(j).setFill(Color.RED);
				try {
					Thread.sleep(600);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if (arrayElements.get(i) > arrayElements.get(j)) {
					swap(i, j);
					swapSquares(i, j);
					final int iIndex = i;
					final int jIndex = j;
					javafx.application.Platform.runLater(() -> {
						animateSwap(iIndex, jIndex);
					});
					try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				squares.get(j).setFill(Color.LIGHTBLUE);
			}
			squares.get(i).setFill(Color.LIGHTBLUE);
		}
	}

	private void swap(int i, int j) {
		int temp = arrayElements.get(i);
		arrayElements.set(i, arrayElements.get(j));
		arrayElements.set(j, temp);
	}

	public void swapSquares(int i, int j) {
		Rectangle temp = squares.get(i);
		squares.set(i, squares.get(j));
		squares.set(j, temp);
	}

	private void animateSwap(int index1, int index2) {
		double totalSpacing = (arrayElements.size() - 1) * squareHBox.getSpacing();
		double squareSize = (squareHBox.getWidth() - totalSpacing) / arrayElements.size();
		StackPane firstStackPane = (StackPane) sortSquareHBox.getChildren().get(index1);
		StackPane secondStackPane = (StackPane) sortSquareHBox.getChildren().get(index2);

		double distanceX = (secondStackPane.getLayoutX() - firstStackPane.getLayoutX());
		double distanceY = squareSize + 5;

		TranslateTransition moveUp1 = new TranslateTransition(Duration.millis(300), firstStackPane);
		TranslateTransition moveRight1 = new TranslateTransition(Duration.millis(300), firstStackPane);
		TranslateTransition moveDown1 = new TranslateTransition(Duration.millis(300), firstStackPane);

		TranslateTransition moveDown2 = new TranslateTransition(Duration.millis(300), secondStackPane);
		TranslateTransition moveLeft2 = new TranslateTransition(Duration.millis(300), secondStackPane);
		TranslateTransition moveUp2 = new TranslateTransition(Duration.millis(300), secondStackPane);

		moveUp1.setByY(-distanceY);
		moveRight1.setByX(distanceX);
		moveDown1.setByY(distanceY);

		moveUp2.setByY(-distanceY);
		moveLeft2.setByX(-distanceX);
		moveDown2.setByY(distanceY);

		SequentialTransition animation1 = new SequentialTransition(moveUp1, moveRight1, moveDown1);
		SequentialTransition animation2 = new SequentialTransition(moveUp2, moveLeft2, moveDown2);

		ParallelTransition parallelAnimation = new ParallelTransition(animation1, animation2);

		parallelAnimation.setOnFinished(e -> {
			List<Node> children = new ArrayList<>(sortSquareHBox.getChildren());
			Node temp = children.get(index1);
			children.set(index1, children.get(index2));
			children.set(index2, temp);

			sortSquareHBox.getChildren().setAll(children);
			firstStackPane.setTranslateX(0);
			firstStackPane.setTranslateY(0);
			secondStackPane.setTranslateX(0);
			secondStackPane.setTranslateY(0);
		});

		parallelAnimation.play();
	}

	public void selectionSort() {
		int size = arrayElements.size();
		for (int i = 0; i < size - 1; i++) {
			int minIndex = i;
			squares.get(i).setFill(Color.RED);
			for (int j = i + 1; j < size; j++) {
				squares.get(j).setFill(Color.GREEN);
				try {
					Thread.sleep(600);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (arrayElements.get(minIndex) > arrayElements.get(j)) {
					if (minIndex != i) {
						squares.get(minIndex).setFill(Color.LIGHTBLUE);
					}
					minIndex = j;
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					squares.get(minIndex).setFill(Color.RED);
				} else {
					squares.get(j).setFill(Color.LIGHTBLUE);
				}
			}
			swapSquares(i, minIndex);
			swap(i, minIndex);
			final int iIndex = i;
			final int j = minIndex;
			javafx.application.Platform.runLater(() -> {
				animateSwap(iIndex, j);
			});
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			squares.get(i).setFill(Color.LIGHTBLUE);
			squares.get(minIndex).setFill(Color.LIGHTBLUE);
		}
	}

	public void bubbleSort() {
		int size = arrayElements.size();
		for (int i = 0; i < size - 1; i++) {
			boolean isSwapped = false;
			for (int j = 0; j < size - i - 1; j++) {
				squares.get(j).setFill(Color.RED);
				squares.get(j + 1).setFill(Color.RED);
				try {
					Thread.sleep(600);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (arrayElements.get(j) > arrayElements.get(j + 1)) {
					isSwapped = true;
					swap(j, j + 1);
					swapSquares(j, j + 1);
					final int firstIndex = j;
					final int secondIndex = j + 1;
					javafx.application.Platform.runLater(() -> {
						animateSwap(firstIndex, secondIndex);
					});
					try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				squares.get(j).setFill(Color.LIGHTBLUE);
				squares.get(j + 1).setFill(Color.LIGHTBLUE);
			}
			if (!isSwapped) {
				break;
			}
		}
	}

	public void insertionSort() {
		int size = arrayElements.size();
		for (int i = 1; i < size; i++) {
			int key = arrayElements.get(i);
			int j = i - 1;
			squares.get(i).setFill(Color.RED);
			try {
				Thread.sleep(600);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			while (j >= 0 && arrayElements.get(j) > key) {
				squares.get(j).setFill(Color.GREEN);
				squares.get(j + 1).setFill(Color.GREEN);
				try {
					Thread.sleep(600);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				arrayElements.set(j + 1, arrayElements.get(j));
				swapSquares(j, j + 1);
				final int jIndex = j;
				javafx.application.Platform.runLater(() -> {
					animateSwap(jIndex, jIndex + 1);
				});
				try {
					Thread.sleep(900);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				squares.get(j).setFill(Color.LIGHTBLUE);
				squares.get(j + 1).setFill(Color.LIGHTBLUE);
				j--;
			}
			arrayElements.set(j + 1, key);
			squares.get(i).setFill(Color.LIGHTBLUE);
			try {
				Thread.sleep(600);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void updateLinePosition(int l, int r) {
		Timeline lineTimeline = new Timeline();
		double totalSpacing = (arrayElements.size() - 1) * squareHBox.getSpacing();
		double squareSize = (squareHBox.getWidth() - totalSpacing) / arrayElements.size();
		if (l >= 0 && r >= 0 && l < squares.size() && r < squares.size()) {
			double leftPosition = l * (squareSize + squareHBox.getSpacing() + 2) + squareSize / 2;
			double rightPosition = r * (squareSize + squareHBox.getSpacing() + 2) + squareSize / 2;
			lineTimeline.stop();
			KeyFrame startFrame = new KeyFrame(Duration.ZERO,
					new KeyValue(sortLine.startXProperty(), sortLine.getStartX()),
					new KeyValue(sortLine.endXProperty(), sortLine.getEndX()));

			KeyFrame endFrame = new KeyFrame(Duration.millis(200),
					new KeyValue(sortLine.startXProperty(), leftPosition),
					new KeyValue(sortLine.endXProperty(), rightPosition));
			lineTimeline.getKeyFrames().setAll(startFrame, endFrame);
			lineTimeline.play();
		}
	}

	public void binaryInsertionSort() {
		int size = arrayElements.size();
		sortLine.setVisible(true);
		for (int i = 1; i < size; i++) {
			int key = arrayElements.get(i);
			squares.get(i).setFill(Color.RED);
			try {
				Thread.sleep(600);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int l = 0;
			int r = i - 1;
			while (l <= r) {
				int m = (l + r) / 2;
				updateLinePosition(l, r);
				squares.get(m).setFill(Color.BLUE);
				try {
					Thread.sleep(600);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (key < arrayElements.get(m)) {
					r = m - 1;
				} else {
					l = m + 1;
				}
				updateLinePosition(l, r);
				squares.get(m).setFill(Color.LIGHTBLUE);
				try {
					Thread.sleep(600);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for (int j = i - 1; j >= l; j--) {
				squares.get(j).setFill(Color.GREEN);
				squares.get(j + 1).setFill(Color.GREEN);
				try {
					Thread.sleep(600);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				arrayElements.set(j + 1, arrayElements.get(j));
				swapSquares(j, j + 1);
				final int jIndex = j;
				javafx.application.Platform.runLater(() -> {
					animateSwap(jIndex, jIndex + 1);
				});
				try {
					Thread.sleep(600);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				squares.get(j).setFill(Color.LIGHTBLUE);
				squares.get(j + 1).setFill(Color.LIGHTBLUE);
			}
			arrayElements.set(l, key);
			squares.get(i).setFill(Color.LIGHTBLUE);
			try {
				Thread.sleep(900);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void shakerSort() {
		int size = arrayElements.size();
		left = 0;
		right = size - 1;
		int k = size - 1;
		while (left < right) {
			squares.get(left).setFill(Color.RED);
			squares.get(right).setFill(Color.RED);
			try {
				Thread.sleep(600);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i = right; i > left; i--) {
				squares.get(i).setFill(Color.GREEN);
				squares.get(i - 1).setFill(Color.GREEN);
				try {
					Thread.sleep(600);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (arrayElements.get(i) < arrayElements.get(i - 1)) {
					swap(i, i - 1);
					swapSquares(i, i - 1);
					final int firstIndex = i;
					final int secondIndex = i - 1;
					javafx.application.Platform.runLater(() -> {
						animateSwap(firstIndex, secondIndex);
					});
					try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					k = i;
				}
				squares.get(i).setFill(Color.LIGHTBLUE);
				squares.get(i - 1).setFill(Color.LIGHTBLUE);
				squares.get(right).setFill(Color.RED);
			}
			left = k;
			for (int i = left; i < right; i++) {
				squares.get(i).setFill(Color.GREEN);
				squares.get(i + 1).setFill(Color.GREEN);
				try {
					Thread.sleep(600);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (arrayElements.get(i) > arrayElements.get(i + 1)) {
					swap(i, i + 1);
					swapSquares(i, i + 1);
					final int firstIndex = i;
					final int secondIndex = i + 1;
					javafx.application.Platform.runLater(() -> {
						animateSwap(firstIndex, secondIndex);
					});
					try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					k = i;
				}
				squares.get(i).setFill(Color.LIGHTBLUE);
				squares.get(i + 1).setFill(Color.LIGHTBLUE);
				squares.get(left).setFill(Color.RED);
			}
			right = k;
		}
	}
	
	public void shellSort() {
		int length = 1;
		while(length <= arrayElements.size() / 3) {
			length = length * 3 + 1;
		}
		while(length > 0) {
			for(int i = length; i < arrayElements.size(); i++) {
				int temp = arrayElements.get(i);
				int j = i - length;
				squares.get(i).setFill(Color.RED);
				squares.get(i - length).setFill(Color.RED);
				try {
					Thread.sleep(600);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				while(j >= 0 && temp < arrayElements.get(j)) {
					squares.get(j).setFill(Color.GREEN);
					squares.get(j + length).setFill(Color.GREEN);
					try {
						Thread.sleep(600);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					arrayElements.set(j + length, arrayElements.get(j));
					swapSquares(j, j + length);
					final int jIndex = j;
					final int nextIndex = j + length;
					javafx.application.Platform.runLater(() -> {
						animateSwap(jIndex, nextIndex);
					});
					try {
						Thread.sleep(900);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					squares.get(j).setFill(Color.LIGHTBLUE);
					squares.get(j + length).setFill(Color.LIGHTBLUE);
					j -= length;
				}
				arrayElements.set(j + length, temp);
				squares.get(i).setFill(Color.LIGHTBLUE);
				squares.get(i - length).setFill(Color.LIGHTBLUE);
				try {
					Thread.sleep(600);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			length = (length - 1) / 3;
		}
	}
}

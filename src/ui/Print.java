package ui;

// Just an alias of the print function to make things easier 
public class Print {
	// Alias new line
	public static void newLine() {
		System.out.println();
	}

    // Alias of text print
	// Parameter: string
	public static void text( String text ) {
		System.out.println( text );
	}

	// Parameter: double
	public static void text( double text ) {
		System.out.println( text );
	}

	// Parameter: integer
	public static void text( int text ) {
		System.out.println( text );
	}

	// Alias
	public static void text( String text, boolean newLine ) {
		if ( !newLine ) {
			System.out.print( text );
		}
		else {
			Print.newLine();
			Print.text( text );
		}
	}
}

package util;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Pattern;


/**
 * <i>Input</i>. This class provides methods for reading strings
 * and numbers from standard input, file input, URLs, and sockets.
 * <p>
 * The Locale used is: language = English, country = US. This is consistent
 * with the formatting conventions with Java floating-point literals,
 * command-line arguments (via {@link Double#parseDouble(String)})
 * and standard output.
 * <p>
 * For additional documentation, see
 * <a href="https://introcs.cs.princeton.edu/31datatype">Section 3.1</a> of
 * <i>Computer Science: An Interdisciplinary Approach</i>
 * by Robert Sedgewick and Kevin Wayne.
 * <p>
 * Like {@link Scanner}, reading a token also consumes preceding Java
 * whitespace, reading a full line consumes
 * the following end-of-line delimiter, while reading a character consumes
 * nothing extra.
 * <p>
 * Whitespace is defined in {@link Character#isWhitespace(char)}. Newlines
 * consist of \n, \r, \r\n, and Unicode hex code points 0x2028, 0x2029, 0x0085;
 * see <a href="http://www.docjar.com/html/api/java/util/Scanner.java.html">
 * Scanner.java</a> (NB: Java 6u23 and earlier uses only \r, \r, \r\n).
 *
 * @author David Pritchard
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */

public final class In {

    //private static final String CHARSET_NAME = "UTF-8";
    private static final Locale LOCALE;
    private static final Pattern WHITESPACE_PATTERN;
    private static final Pattern EMPTY_PATTERN;
    private static final Pattern EVERYTHING_PATTERN;

    static {
        LOCALE = Locale.US;
        WHITESPACE_PATTERN = Pattern.compile("\\p{javaWhitespace}+");
        EMPTY_PATTERN = Pattern.compile("");
        EVERYTHING_PATTERN = Pattern.compile("\\A");
    }

    private final Scanner scanner;

    public In() {
        this.scanner = new Scanner(new BufferedInputStream(System.in), StandardCharsets.UTF_8);
        this.scanner.useLocale(LOCALE);
    }

    public In(final Socket var1) {
        if (var1 == null) {
            throw new IllegalArgumentException("socket argument is null");
        } else {
            try {
                final InputStream var2 = var1.getInputStream();
                this.scanner = new Scanner(new BufferedInputStream(var2), StandardCharsets.UTF_8);
                this.scanner.useLocale(LOCALE);
            } catch (final IOException var3) {
                throw new IllegalArgumentException("Could not open " + var1, var3);
            }
        }
    }

    public In(final URL var1) {
        if (var1 == null) {
            throw new IllegalArgumentException("url argument is null");
        } else {
            try {
                final URLConnection var2 = var1.openConnection();
                final InputStream var3 = var2.getInputStream();
                this.scanner = new Scanner(new BufferedInputStream(var3), StandardCharsets.UTF_8);
                this.scanner.useLocale(LOCALE);
            } catch (final IOException var4) {
                throw new IllegalArgumentException("Could not open " + var1, var4);
            }
        }
    }

    public In(final File var1) {
        if (var1 == null) {
            throw new IllegalArgumentException("file argument is null");
        } else {
            try {
                final FileInputStream var2 = new FileInputStream(var1);
                this.scanner = new Scanner(new BufferedInputStream(var2), StandardCharsets.UTF_8);
                this.scanner.useLocale(LOCALE);
            } catch (final IOException var3) {
                throw new IllegalArgumentException("Could not open " + var1, var3);
            }
        }
    }

    public In(final String var1) {
        if (var1 == null) {
            throw new IllegalArgumentException("argument is null");
        } else {
            try {
                final File var2 = new File(var1);
                if (var2.exists()) {
                    final FileInputStream var7 = new FileInputStream(var2);
                    this.scanner = new Scanner(new BufferedInputStream(var7), StandardCharsets.UTF_8);
                } else {
                    URL var3 = this.getClass().getResource(var1);
                    if (var3 == null) {
                        var3 = this.getClass().getClassLoader().getResource(var1);
                    }

                    if (var3 == null) {
                        var3 = new URL(var1);
                    }

                    final URLConnection var4 = var3.openConnection();
                    final InputStream var5 = var4.getInputStream();
                    this.scanner = new Scanner(new BufferedInputStream(var5), StandardCharsets.UTF_8);
                }
                this.scanner.useLocale(LOCALE);
            } catch (final IOException var6) {
                throw new IllegalArgumentException("Could not open " + var1, var6);
            }
        }
    }

    public In(final Scanner var1) {
        if (var1 == null) {
            throw new IllegalArgumentException("scanner argument is null");
        } else {
            this.scanner = var1;
        }
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static int[] readInts(final String var0) {
        return (new In(var0)).readAllInts();
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static double[] readDoubles(final String var0) {
        return (new In(var0)).readAllDoubles();
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static String[] readStrings(final String var0) {
        return (new In(var0)).readAllStrings();
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static int[] readInts() {
        return (new In()).readAllInts();
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static double[] readDoubles() {
        return (new In()).readAllDoubles();
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static String[] readStrings() {
        return (new In()).readAllStrings();
    }

    public static void main(final String[] var0) {
        final String var2 = "https://introcs.cs.princeton.edu/java/stdlib/InTest.txt";
        System.out.println("readAll() from URL " + var2);
        System.out.println("---------------------------------------------------------------------------");

        In var1;
        try {
            var1 = new In(var2);
            System.out.println(var1.readAll());
        } catch (final IllegalArgumentException var4) {
            var4.printStackTrace();
        }

        System.out.println();
        System.out.println("readLine() from URL " + var2);
        System.out.println("---------------------------------------------------------------------------");

        String var3;
        try {
            var1 = new In(var2);

            while (!var1.isEmpty()) {
                var3 = var1.readLine();
                System.out.println(var3);
            }
        } catch (final IllegalArgumentException var11) {
            var11.printStackTrace();
        }

        System.out.println();
        System.out.println("readString() from URL " + var2);
        System.out.println("---------------------------------------------------------------------------");

        try {
            var1 = new In(var2);

            while (!var1.isEmpty()) {
                var3 = var1.readString();
                System.out.println(var3);
            }
        } catch (final IllegalArgumentException var10) {
            var10.printStackTrace();
        }

        System.out.println();
        System.out.println("readLine() from current directory");
        System.out.println("---------------------------------------------------------------------------");

        try {
            var1 = new In("./InTest.txt");

            while (!var1.isEmpty()) {
                var3 = var1.readLine();
                System.out.println(var3);
            }
        } catch (final IllegalArgumentException var9) {
            var9.printStackTrace();
        }

        System.out.println();
        System.out.println("readLine() from relative path");
        System.out.println("---------------------------------------------------------------------------");

        try {
            var1 = new In("../stdlib/InTest.txt");

            while (!var1.isEmpty()) {
                var3 = var1.readLine();
                System.out.println(var3);
            }
        } catch (final IllegalArgumentException var8) {
            var8.printStackTrace();
        }

        System.out.println();
        System.out.println("readChar() from file");
        System.out.println("---------------------------------------------------------------------------");

        try {
            var1 = new In("InTest.txt");

            while (!var1.isEmpty()) {
                final char var12 = var1.readChar();
                System.out.print(var12);
            }
        } catch (final IllegalArgumentException var7) {
            var7.printStackTrace();
        }

        System.out.println();
        System.out.println();
        System.out.println("readLine() from absolute OS X / Linux path");
        System.out.println("---------------------------------------------------------------------------");

        try {
            var1 = new In("/n/fs/introcs/www/java/stdlib/InTest.txt");

            while (!var1.isEmpty()) {
                var3 = var1.readLine();
                System.out.println(var3);
            }
        } catch (final IllegalArgumentException var6) {
            var6.printStackTrace();
        }

        System.out.println();
        System.out.println("readLine() from absolute Windows path");
        System.out.println("---------------------------------------------------------------------------");

        try {
            var1 = new In("G:\\www\\introcs\\stdlib\\InTest.txt");

            while (!var1.isEmpty()) {
                var3 = var1.readLine();
                System.out.println(var3);
            }

            System.out.println();
        } catch (final IllegalArgumentException var5) {
            var5.printStackTrace();
        }

        System.out.println();
    }

    public boolean exists() {
        return this.scanner != null;
    }

    public boolean isEmpty() {
        return !this.scanner.hasNext();
    }

    public boolean hasNextLine() {
        return this.scanner.hasNextLine();
    }

    public boolean hasNextChar() {
        this.scanner.useDelimiter(EMPTY_PATTERN);
        final boolean var1 = this.scanner.hasNext();
        this.scanner.useDelimiter(WHITESPACE_PATTERN);
        return var1;
    }

    public String readLine() {
        String var1;
        try {
            var1 = this.scanner.nextLine();
        } catch (final NoSuchElementException var3) {
            var1 = null;
        }

        return var1;
    }

    public char readChar() {
        this.scanner.useDelimiter(EMPTY_PATTERN);

        try {
            final String var1 = this.scanner.next();

            assert var1.length() == 1 : "Internal (Std)In.readChar() error! Please contact the authors.";

            this.scanner.useDelimiter(WHITESPACE_PATTERN);
            return var1.charAt(0);
        } catch (final NoSuchElementException var2) {
            throw new NoSuchElementException(
                    "attempts to read a 'char' value from the input stream, but no more tokens are available");
        }
    }

    public String readAll() {
        if (!this.scanner.hasNextLine()) {
            return "";
        } else {
            final String var1 = this.scanner.useDelimiter(EVERYTHING_PATTERN).next();
            this.scanner.useDelimiter(WHITESPACE_PATTERN);
            return var1;
        }
    }

    public String readString() {
        try {
            return this.scanner.next();
        } catch (final NoSuchElementException var2) {
            throw new NoSuchElementException(
                    "attempts to read a 'String' value from the input stream, but no more tokens are available");
        }
    }

    public int readInt() {
        try {
            return this.scanner.nextInt();
        } catch (final InputMismatchException var3) {
            final String var2 = this.scanner.next();
            throw new InputMismatchException(
                    "attempts to read an 'int' value from the input stream, but the next token is \"" + var2 + "\"");
        } catch (final NoSuchElementException var4) {
            throw new NoSuchElementException(
                    "attempts to read an 'int' value from the input stream, but no more tokens are available");
        }
    }

    public double readDouble() {
        try {
            return this.scanner.nextDouble();
        } catch (final InputMismatchException var3) {
            final String var2 = this.scanner.next();
            throw new InputMismatchException(
                    "attempts to read a 'double' value from the input stream, but the next token is \"" + var2 + "\"");
        } catch (final NoSuchElementException var4) {
            throw new NoSuchElementException(
                    "attempts to read a 'double' value from the input stream, but no more tokens are available");
        }
    }

    public float readFloat() {
        try {
            return this.scanner.nextFloat();
        } catch (final InputMismatchException var3) {
            final String var2 = this.scanner.next();
            throw new InputMismatchException(
                    "attempts to read a 'float' value from the input stream, but the next token is \"" + var2 + "\"");
        } catch (final NoSuchElementException var4) {
            throw new NoSuchElementException(
                    "attempts to read a 'float' value from the input stream, but no more tokens are available");
        }
    }

    public long readLong() {
        try {
            return this.scanner.nextLong();
        } catch (final InputMismatchException var3) {
            final String var2 = this.scanner.next();
            throw new InputMismatchException(
                    "attempts to read a 'long' value from the input stream, but the next token is \"" + var2 + "\"");
        } catch (final NoSuchElementException var4) {
            throw new NoSuchElementException(
                    "attempts to read a 'long' value from the input stream, but no more tokens are available");
        }
    }

    public short readShort() {
        try {
            return this.scanner.nextShort();
        } catch (final InputMismatchException var3) {
            final String var2 = this.scanner.next();
            throw new InputMismatchException(
                    "attempts to read a 'short' value from the input stream, but the next token is \"" + var2 + "\"");
        } catch (final NoSuchElementException var4) {
            throw new NoSuchElementException(
                    "attempts to read a 'short' value from the input stream, but no more tokens are available");
        }
    }

    public byte readByte() {
        try {
            return this.scanner.nextByte();
        } catch (final InputMismatchException var3) {
            final String var2 = this.scanner.next();
            throw new InputMismatchException(
                    "attempts to read a 'byte' value from the input stream, but the next token is \"" + var2 + "\"");
        } catch (final NoSuchElementException var4) {
            throw new NoSuchElementException(
                    "attempts to read a 'byte' value from the input stream, but no more tokens are available");
        }
    }

    public boolean readBoolean() {
        try {
            final String var1 = this.readString();
            if ("true".equalsIgnoreCase(var1)) {
                return true;
            } else if ("false".equalsIgnoreCase(var1)) {
                return false;
            } else if ("1".equals(var1)) {
                return true;
            } else if ("0".equals(var1)) {
                return false;
            } else {
                throw new InputMismatchException(
                        "attempts to read a 'boolean' value from the input stream, but the next token is \"" + var1
                                + "\"");
            }
        } catch (final NoSuchElementException var2) {
            throw new NoSuchElementException(
                    "attempts to read a 'boolean' value from the input stream, but no more tokens are available");
        }
    }

    public String[] readAllStrings() {
        final String[] var1 = WHITESPACE_PATTERN.split(this.readAll());
        if (var1.length != 0 && var1[0].length() <= 0) {
            final String[] var2 = new String[var1.length - 1];

            System.arraycopy(var1, 1, var2, 0, var1.length - 1);

            return var2;
        } else {
            return var1;
        }
    }

    public String[] readAllLines() {
        final ArrayList<String> var1 = new ArrayList<>();

        while (this.hasNextLine()) {
            var1.add(this.readLine());
        }

        return var1.toArray(new String[0]);
    }

    public int[] readAllInts() {
        final String[] var1 = this.readAllStrings();
        final int[] var2 = new int[var1.length];

        for (int var3 = 0; var3 < var1.length; ++var3) {
            var2[var3] = Integer.parseInt(var1[var3]);
        }

        return var2;
    }

    public long[] readAllLongs() {
        final String[] var1 = this.readAllStrings();
        final long[] var2 = new long[var1.length];

        for (int var3 = 0; var3 < var1.length; ++var3) {
            var2[var3] = Long.parseLong(var1[var3]);
        }

        return var2;
    }

    public double[] readAllDoubles() {
        final String[] var1 = this.readAllStrings();
        final double[] var2 = new double[var1.length];

        for (int var3 = 0; var3 < var1.length; ++var3) {
            var2[var3] = Double.parseDouble(var1[var3]);
        }

        return var2;
    }

    public void close() {
        this.scanner.close();
    }
}

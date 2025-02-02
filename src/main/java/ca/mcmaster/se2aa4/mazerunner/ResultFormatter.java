package ca.mcmaster.se2aa4.mazerunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class ResultFormatter {
    private static final Logger logger = LogManager.getLogger(ResultFormatter.class);

    public String factorizePath(String path) {
        logger.debug("Starting path factorization for path: " + path);
        StringBuilder factorizedPath = new StringBuilder();
        int count = 1;

        for (int i = 1; i < path.length(); i++) {
            if (path.charAt(i) == path.charAt(i - 1)) {
                count++;
            } else {
                appendFactorizedSegment(factorizedPath, count, path.charAt(i - 1));
                count = 1;
            }
        }
        appendFactorizedSegment(factorizedPath, count, path.charAt(path.length() - 1));

        logger.info("Path factorization completed: " + factorizedPath.toString());
        return factorizedPath.toString();
    }

    private void appendFactorizedSegment(StringBuilder factorizedPath, int count, char move) {
        if (count > 1) {
            factorizedPath.append(count);
        }
        factorizedPath.append(move).append(" "); // Adding space after each segment
    }
}

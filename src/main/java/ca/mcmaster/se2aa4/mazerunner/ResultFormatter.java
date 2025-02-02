package ca.mcmaster.se2aa4.mazerunner;

class ResultFormatter {

    public String factorizePath(String path) {
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
        return factorizedPath.toString();
    }

    private void appendFactorizedSegment(StringBuilder factorizedPath, int count, char move) {
        if (count > 1) {
            factorizedPath.append(count);
        }
        factorizedPath.append(move).append(" ");
    }
}

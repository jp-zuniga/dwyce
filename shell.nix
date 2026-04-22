{pkgs ? import <nixpkgs> {}}:
pkgs.mkShell {
  buildInputs = [
    pkgs.prettier
    pkgs.unzip
    pkgs.zip
  ];
}

const outputChannel = window.createOutputChannel(`My Test`);
  outputChannel.append('Test');
  outputChannel.show();

  setTimeout(() => {
    outputChannel.show();
  }, 5000);